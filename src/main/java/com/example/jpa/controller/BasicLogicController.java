package com.example.jpa.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Arrays;

@RestController
public class BasicLogicController {
    
    @PostMapping("sort")
    public BasicLogic getSort( @RequestBody BasicLogic basicLogic) {
        Arrays.sort(basicLogic.arrayInt);
        return basicLogic;
    }

    @PostMapping("sort-and-double")
    public BasicLogic getSortDoubled(BasicLogic basicLogic) {
        Arrays.sort(basicLogic.arrayInt);
        for(int i = 0; i < basicLogic.arrayInt.length; i++) {
            basicLogic.arrayInt[i]=basicLogic.arrayInt[i]*2;
        }
        return basicLogic;
    }
}

class BasicLogic {
    public int[] arrayInt;

    public BasicLogic() {
    }

    public BasicLogic(int[] arrayInt) {
        this.arrayInt = arrayInt;
    }

    public int[] getArrayInt() {
        return this.arrayInt;
    }

    public void setArrayInt(int[] arrayInt) {
        this.arrayInt = arrayInt;
    }

    public BasicLogic arrayInt(int[] arrayInt) {
        this.arrayInt = arrayInt;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " arrayInt='" + getArrayInt() + "'" +
            "}";
    }
    
}
