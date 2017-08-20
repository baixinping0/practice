package com.baixinping.cvtepro.controller;

import javax.annotation.Resource;

import com.baixinping.cvtepro.service.LogiscustomEbi;
import org.springframework.stereotype.Controller;


@Controller
public class LogiscustomController {
	@Resource
	LogiscustomEbi logiscustomEbi;
}
