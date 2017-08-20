package com.baixinping.cvtepro.controller;

import javax.annotation.Resource;

import com.baixinping.cvtepro.service.LogissendEbi;
import org.springframework.stereotype.Controller;

@Controller
public class LogissendController {
	@Resource
	LogissendEbi logissendEbi;
}
