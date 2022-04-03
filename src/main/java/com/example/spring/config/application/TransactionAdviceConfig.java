package com.example.spring.config.application;

import java.util.Collections;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.example.spring.config.AppConstants;

import lombok.RequiredArgsConstructor;

/**
 * Configure transaction advice using Spring AOP.
 */
@Configuration
@EnableAspectJAutoProxy
@RequiredArgsConstructor
public class TransactionAdviceConfig {

	private final TransactionManager transactionManager;

	/**
	 * Configure transaction rules and register as bean.
	 * @return TransactionInterceptor object.
	 */
	@Bean
	public TransactionInterceptor txAdvice() {
		RuleBasedTransactionAttribute tx = new RuleBasedTransactionAttribute();
		tx.setName("NORMAL_TRANSACTION");
		tx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
		tx.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);
		tx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		tx.setTimeout(-1);

		RuleBasedTransactionAttribute roTx = new RuleBasedTransactionAttribute();
		roTx.setName("READONLY_TRANSACTION");
		roTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
		roTx.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);
		roTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		roTx.setReadOnly(true);
		roTx.setTimeout(-1);

		NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
		source.addTransactionalMethod("txGet*", roTx);
		source.addTransactionalMethod("txFind*", roTx);
		source.addTransactionalMethod("txSelect*", roTx);
		source.addTransactionalMethod("*", tx);

		TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, source);
		return txAdvice;
	}

	/**
	 * Configure transaction advice and register as bean.
	 * @return Advisor object.
	 */
	@Bean
	public Advisor txPointcutAdvisor() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(AppConstants.TRANSACTION_POINTCUT_EXPRESSION);
		return new DefaultPointcutAdvisor(pointcut, txAdvice());
	}

}
