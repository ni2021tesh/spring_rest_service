// Copyright (c) 2018 Travelex Ltd

package info.niteshjha.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"greet i18n resource"})
public class GreetController {

    @Autowired
    private MessageSource messageSource;

    @ApiOperation(value = "get i18n message", notes = "this endpoint provide an i18n message based on locale")
    @GetMapping(value = "/greet")
    public String greet() {
        return messageSource.getMessage("greet.message", null, LocaleContextHolder.getLocale());
    }
}
