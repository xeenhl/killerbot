package com.tupocode.killerbot.actions

import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.beans.factory.getBeansOfType
import org.springframework.context.support.AbstractApplicationContext
import org.springframework.stereotype.Component

@Component
class ActionManagerBeanPostProcessor(val applicationContext: AbstractApplicationContext) : BeanPostProcessor {
    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        if(bean is ActionManager) {
            applicationContext.getBeansOfType<Action<Any>>().map { it.value }.forEach { bean.actions[it.label()] = it }
        }
        return super.postProcessAfterInitialization(bean, beanName)
    }
}