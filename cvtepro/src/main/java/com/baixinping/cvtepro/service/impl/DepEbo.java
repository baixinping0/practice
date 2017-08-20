package com.baixinping.cvtepro.service.impl;

import java.io.Serializable;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.baixinping.cvtepro.dao.DepDao;
import com.baixinping.cvtepro.entity.DepModel;
import com.baixinping.cvtepro.entity.EmpModel;
import com.baixinping.cvtepro.entity.MenuModel;
import com.baixinping.cvtepro.service.DepEbi;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class DepEbo implements DepEbi {
    @Resource
    DepDao depDao;

    @Override
    public List<DepModel> list() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DepModel get(Serializable id) {
        return depDao.get(id);
    }

    @Override
    public void insert(DepModel entity) {
        // TODO Auto-generated method stub
    }

    @Override
    public void update(DepModel entity) {
        // TODO Auto-generated method stub
    }

    @Override
    public void deleteById(Serializable id) {
        // TODO Auto-generated method stub
    }

    @Override
    public void delete(Serializable[] ids) {
        // TODO Auto-generated method stub
    }

    @Override
    public List<DepModel> listByCharger(String user_code) {
        return depDao.listByCharger(user_code);
    }

    @Override
    public List<DepModel> listAuthDep(String user_code) {
        return depDao.listAuthDep(user_code);
    }

    /**
     * 根据用户的信息，获取此用户所有权限管理的所有部门权限树信息
     */
    @Override
    @Pointcut
    public List<DepModel> listDepByEmp(EmpModel empModel) {
        List<DepModel> srcDepList = listDepSrcByEmp(empModel);
        return extend(srcDepList);
    }

    private List<DepModel> listDepSrcByEmp(EmpModel empModel){
        DepModel currentDep = get(empModel.getDept_code().trim());
        List<DepModel> chargerDepList = listByCharger(empModel.getId());
        //通过用户的信息获取给用户单独授权的部门信息
        //select * from cvte_dept where dept_code in (select dept_code from cvte_user_dept where user_code = 100001)
        List<DepModel> authDepList = listAuthDep(empModel.getId());
        //对以上的所有部门信息进行合并去重处理，得到list集合
        Set<DepModel> depSet = new HashSet<>(chargerDepList);
        depSet.add(currentDep);
        depSet.addAll(authDepList);
        return new ArrayList<>(depSet);
    }

    /**
     * 通过用户直接管理的部门信息，向上，向下进行扩展获取，获取用户最终具有权限管理的权限树
     */
        private List<DepModel> extend(List<DepModel> srcList) {
        //获取已有部门的所有子部门
        List<DepModel> childList1 = extendChild(srcList);
        //获取已有部门的父部门
        List<DepModel> parentList = extendParent(srcList);
//        List<DepModel> parentList = extendParent1(srcList);

        //整合最终结果并去重处理
        Set<DepModel> depSet = new HashSet<>(childList1);
        depSet.addAll(parentList);
        return new ArrayList<>(depSet);
    }

    /**
     * 通过已有的部门信息，向上扩展父部门信息,最终获取的节点包含原节点信息
     *
     * @param srcList
     * @return
     */

    private List<DepModel> extendParent(List<DepModel> srcList) {
        //如果只有一个部门的情况：
        if (srcList.size() <= 1)
            return srcList;
        String[][] path = extendParentPath(srcList);
        Integer rootIndex = getFinalColEqueals(path);
        //从根节点往后获取数据
        List<DepModel> resultList = null;
        if (rootIndex != null)
            resultList = getDepListByPathAndCol(path, rootIndex);
        return resultList;
    }

    /**
     * 通过已有的父路径和列下标获取此列往后的所有的部门信息
     *
     * @return
     */
    private List<DepModel> getDepListByPathAndCol(String[][] path, Integer colIndel) {
        List<DepModel> resultList = new ArrayList<>();
        for (int i = 0; i < path.length; i++) {
            for (int j = colIndel; j < path[0].length; j++) {
                //从数据库中查找数据
                if (path[i][j] != null) {
                    DepModel d = get(path[i][j]);
                    resultList.add(d);
                }
            }
        }
        return resultList;
    }

    /**
     *优化后扩展父类
     * @param srcList
     * @return
     */
    private List<DepModel> extendParent1(List<DepModel> srcList){
        return extendParentById(extendParentIdByRootIndex(srcList, getRootIndex(srcList)));
    }

    /**
     * 获取一批部门中，部门id第一个不相等的下标,使用subString(0, i)就可以获得最近的根
     * @param srcList
     * @return
     */
    private int getRootIndex(List<DepModel> srcList) {
        String baseId = srcList.get(0).getId();
        for (int j = 3; j < baseId.length(); j+=2) {
            String start = baseId.substring(0,j);
            for (int i = 0; i < srcList.size(); i++) {
                if(!srcList.get(i).getId().startsWith(start)){
                    return j - 2;
                }
            }
        }
        //保证长度为1节点返回正确的结果
        return baseId.length() == 1 ? 1 : baseId.length() - 2;
    }

    private List<DepModel> extendParentById(List<String> ids){
        List<DepModel> resList = new ArrayList<>();
        for(String id : ids)
            resList.add(get(id));
        return resList;
    }
    /**
     *通过给定的部门集合和id下标获取父部门集合
     * @param srcList
     * @param index
     * @return
     */
    private List<String> extendParentIdByRootIndex(List<DepModel> srcList, int index){
        List<String> depList = new ArrayList<>();
        //获取根避免重复获取
        depList.add(srcList.get(0).getId().substring(0, index));
        for (DepModel d : srcList){
            String id = d.getId();
            //+2为了跳过根节点，id.length()为了避免获取yuan节点
            for(int j = index + 2; j < id.length(); j += 2){
                String cuId = id.substring(0, j);
                depList.add(cuId);
            }
        }
        return new ArrayList<>(new HashSet<>(depList));
    }

    /**
     * 获取一个二维数据中第一列不完全相等的行下标-1的
     * @return
     */
    private Integer getFinalColEqueals(String[][] path) {
        Integer rootIndex = null;
        boolean isEnd = false;
        if (path.length > 0)
            for (int j = 0; j < path[0].length; j++) {
                if (isEnd) break;
                String value = path[0][j];
                for (int i = 0; i < path.length; i++)
                    if (path[i][j] == null || !value.equals(path[i][j])) {
                        rootIndex = j - 1;
                        isEnd = true;
                        break;
                    }
            }
        return rootIndex;
    }

    /**
     * 对部门的id进行切割，获取所有元结点到达父结点的路径
     *
     * @param srcList
     * @return 1
     * 101
     * 10101
     * 102
     * 10201
     * 1020101
     */
    private String[][] extendParentPath(List<DepModel> srcList) {
        String[][] path = new String[srcList.size()][];
        for (int i = 0; i < srcList.size(); i++) {
            String code = srcList.get(i).getId();
            path[i] = splitCode(code);
        }
        return path;
    }

    /**
     * 对部门id进行切割
     *
     * @param code
     * @return
     */
    private String[] splitCode(String code) {
        String[] path = new String[20];
        for (int i = 1, index = 0; index < (code.length() + 1) / 2; i += 2, index++) {
            path[index] = code.substring(0, i);
        }
        return path;
    }

    /**
     * 获取子节点方法一，此方法是通过迭代循环的方式进行获取
     *
     * @param srcList
     * @param resultList
     */
    private void extendChild(List<DepModel> srcList, List<DepModel> resultList) {
        resultList.addAll(srcList);
        if (srcList.size() == 0)
            return;
        for (DepModel depModel : srcList) {
            List<DepModel> depList = depDao.listChildByParent(depModel.getId());
            extendChild(depList, resultList);
        }
        return;
    }

    /**
     * 获取子结点方法二
     * 此方法是直接根据id的特性进行获取
     *
     * @param srcList
     * @return
     */
    private List<DepModel> extendChild(List<DepModel> srcList) {
        List<DepModel> resultList = new ArrayList<>();
        for (DepModel depModel : srcList) {
            List<DepModel> depList = depDao.listAllChildByParent(depModel.getId());
            resultList.addAll(depList);
        }
        return resultList;
    }

    /**
     * 对list集合中的部门进行去重处理，此方法已经废弃，直接使用set集合替代
     *
     * @param depList
     * @return
     */
    @Deprecated
    private List<DepModel> distinct(List<DepModel> depList) {
        HashMap<String, DepModel> depMap = new HashMap<>();
        for (DepModel depModel : depList) {
            if (depModel != null) {
                depMap.put(depModel.getId(), depModel);
            }
        }
        return new ArrayList<>(depMap.values());
    }

    /**
     * 根据一级菜单id获取二级菜单
     * @param session
     * @param parent_id
     * @return
     */
    public List<MenuModel> getLeavel2Menu(HttpSession session, String parent_id){
        List<MenuModel> menus = new ArrayList<>();
        List<DepModel> srcList = (List<DepModel>) session.getAttribute(EmpModel.EMP_DEP_LIST);
        List<DepModel> depList = getLevel2DepList(parent_id, srcList);
        for (DepModel depModel : depList) {
            menus.add(new MenuModel(depModel.getId(),depModel.getDept_name(),
                    "folder",true));
        }
        return menus;
    }


    /**
     * 此方法是为菜单展示提供一级根菜单
     * @param session
     * @param dept_name
     * @return
     */
    public List<MenuModel> getRootMenu(HttpSession session, String dept_name){
        List<MenuModel> menus = new ArrayList<>();
        List<DepModel> depList = (List<DepModel>) session.getAttribute(EmpModel.EMP_DEP_LIST_SEARCH);
        if(!StringUtils.isEmpty(dept_name.trim()))
            depList = searchDepList(depList, dept_name);
        session.setAttribute(EmpModel.EMP_DEP_LIST, depList);
        DepModel rootDep  = getOneRoot(depList);
        menus.add(new MenuModel(rootDep.getId(),rootDep.getDept_name(),
                "folder",true));
        return menus;
    }
    /**
     * 根据有规律的一批部门信息获取这批部门中的根部门
     * @param srcList
     * @return
     */
    private DepModel getOneRoot(List<DepModel> srcList){
        DepModel rootDep  = srcList.get(0);
        for (DepModel depModel : srcList) {
            if(depModel.getId().length() < rootDep.getId().length())
                rootDep = depModel;
        }
        return rootDep;
    }
    /**
     * 根据上一级菜单获取下一级菜单
     * @param parent_code
     * @return
     */
    private List<DepModel> getLevel2DepList(String parent_code, List<DepModel> depList){
        List<DepModel> resultList = new ArrayList<>();
        if (depList != null)
            for (DepModel depModel : depList) {
                if(depModel.getParent_code() != null && depModel.getParent_code().equals(parent_code))
                    resultList.add(depModel);
            }
        return resultList;
    }
    /**要查询的条件对部门的数据进行过滤
     * 根据
     * @param srcList
     * @return
     */
    private List<DepModel> searchDepList(List<DepModel> srcList, String dept_name){
        List<DepModel> resultList = new ArrayList<>();
        for (int i = 0; i < srcList.size(); i++) {
            String cname = srcList.get(i).getDept_name();
            if(cname.contains(dept_name)){
                resultList.add(srcList.get(i));
            }
        }
        resultList = extend(resultList);
        //对扩展后的节点信息进行去重处理
        Set<DepModel> depSet = new HashSet<>(resultList);
        resultList = new ArrayList<>(depSet);
        return resultList;
    }

}
