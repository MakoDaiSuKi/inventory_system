package com.inventory.common;

import com.jfinal.core.Controller;
import com.inventory.dto.JsonResult;

public class BaseController extends Controller{

    protected void commonSuccessJson() {
        renderJson(new JsonResult(true));
    }
}

