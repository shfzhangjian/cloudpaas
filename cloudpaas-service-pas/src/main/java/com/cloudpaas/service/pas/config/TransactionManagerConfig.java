package com.cloudpaas.service.pas.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 *
 * 分布式事务
 * @author yu on 2017/12/28.
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
public class TransactionManagerConfig {

	@Bean(name = "userTransaction")
	public UserTransaction userTransaction() throws Throwable {
		UserTransactionImp userTransactionImp = new UserTransactionImp();
		userTransactionImp.setTransactionTimeout(10000);
		return userTransactionImp;
	}

	@Bean(name = "atomikosTransactionManager", initMethod = "init", destroyMethod = "close")
	public TransactionManager atomikosTransactionManager() throws Throwable {
		UserTransactionManager userTransactionManager = new UserTransactionManager();
		userTransactionManager.setForceShutdown(false);
		return userTransactionManager;
	}

	@Bean(name = "transactionManager")
	@DependsOn({ "userTransaction", "atomikosTransactionManager" })
	public PlatformTransactionManager transactionManager() throws Throwable {
		UserTransaction userTransaction = userTransaction();
		JtaTransactionManager manager = new JtaTransactionManager(userTransaction,atomikosTransactionManager());
		return manager;
	}

}