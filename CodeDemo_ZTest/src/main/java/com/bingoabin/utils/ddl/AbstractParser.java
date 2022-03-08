package com.bingoabin.utils.ddl;

import java.util.Stack;

/**
 * @author ouzhenwei
 * @ClassName AbstractParser
 * @Description
 * @createTime 2021-07-29 17:28:07
 */
public abstract class AbstractParser {

    protected final static char EMPTY = '0';

    private Stack<Character> symbolStack = new Stack<>();

    protected void push(char c){
        symbolStack.push(c);
    }

    protected void pop(){
        symbolStack.pop();
    }

    protected char getTopChar(){
        if(symbolStack.size() == 0){
            return EMPTY;
        }
        return symbolStack.peek();
    }

    protected void clearStack(){
        symbolStack.clear();
    }
}
