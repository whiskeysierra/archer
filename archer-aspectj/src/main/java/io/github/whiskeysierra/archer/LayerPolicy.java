package io.github.whiskeysierra.archer;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareError;

@Aspect
public final class LayerPolicy {

    @DeclareError("staticinitialization(!@(@Layer *) *)")
    public static final String MISSING_LAYER = "must be part of a layer";

    @DeclareError("within(@Gateway *) && call(* (@(@Layer *) !@(Gateway || Library || Model) *).*(..))")
    public static final String GATEWAY = "may only call @Gateway, @Library and @Model";
    
    @DeclareError("within(@Library *) && call(* (@(@Layer *) !@(Library || Model) *).*(..))")
    public static final String LIBRARY = "may only call @Library and @Model";
    
    @DeclareError("within(@Logic *) && call(* (@(@Layer *) !@(Gateway || Library || Logic || Model || Persistence) *).*(..))")
    public static final String LOGIC = "may only call @Gateway, @Library, @Logic, @Model and @Persistence";
    
    @DeclareError("within(@Model *) && call(* (@(@Layer *) !@(Library || Model) *).*(..))")
    public static final String MODEL = "may only call @Library and @Model";
    
    @DeclareError("within(@Persistence *) && call(* (@(@Layer *) !@(Library || Model || Persistence) *).*(..))")
    public static final String PERSISTENCE = "may only call @Library, @Model and @Persistence";
    
    @DeclareError("within(@Queue *) && call(* (@(@Layer *) !@(Library || Logic || Model || Queue) *).*(..))")
    public static final String QUEUE = "may only call @Library, @Logic, @Model and @Queue";

    @DeclareError("within(@Resource *) && call(* (@(@Layer *) !@(Gateway || Library || Logic || Model || Persistence || Resource) *).*(..))")
    public static final String RESOURCE = "may only call @Gateway, @Library, @Logic, @Model, @Persistence and @Resource";
    
    @DeclareError("within(@Scheduler *) && call(* (@(@Layer *) !@(Library || Logic || Model || Scheduler) *).*(..))")
    public static final String SCHEDULER = "may only call @Library, @Logic, @Model and @Scheduler";

    private LayerPolicy() {

    }

}
