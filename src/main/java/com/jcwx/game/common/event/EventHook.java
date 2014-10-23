package com.jcwx.game.common.event;

import java.util.ArrayList;
import java.util.List;

public class EventHook<T> implements Hook{
    private List<Hook> hooks = new ArrayList<Hook>();
    
    public void addHook(Hook hook){
	hooks.add(hook);
    }


    @Override
    public void trigger() throws Exception{
	
	for(Hook hook : hooks){
	    try {
		hook.trigger();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }
}
