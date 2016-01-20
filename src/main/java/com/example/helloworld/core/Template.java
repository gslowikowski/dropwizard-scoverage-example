package com.example.helloworld.core;

import com.google.common.base.Optional;

import static java.lang.String.format;

public class Template {
    private final String content;
    private final String defaultName;

    public Template(String content, String defaultName) {
        this.content = content;
        this.defaultName = defaultName;
    }
    
    public String render(Optional<String> name) {
        //GS return format(content, name.or(defaultName));
        return format(content, String.format("%s (%s)", name.or(defaultName), service.HelloServiceScala$.MODULE$.hello())); //GS
    }
}
