package com.jojoldu.aws.springbootsqs.annotation;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * Created by jojoldu@gmail.com on 2018. 3. 17.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
@Conditional(OnMockSqsCondition.class)
public @interface ConditionalOnMockSqs {
}
