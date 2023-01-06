package com.formacionbdi.microservicios.app.heroes.configuration;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.formacionbdi.microservicios.app.heroes.utils.Constants;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class DataSourceConfiguration {

    @Primary
    @Bean(name = Constants.DATASOURCE_H2_NAME)
    @ConfigurationProperties(prefix = Constants.PREFIX_MAIN_DATASOURCE)
    public DataSource getDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = Constants.LIQUIBASE_PROPERTIES_NAME)
    @ConfigurationProperties(prefix = Constants.PREFIX_MAIN_CHANGELOG)
    public LiquibaseProperties getLiquibaseProperties(){
        return new LiquibaseProperties();
    }

    @Bean(name = Constants.SPRING_LIQUIBASE_H2)
    public SpringLiquibase liquibaseConfiguration(){
        return configureLiquibase(getDataSource(),getLiquibaseProperties());
    }

    public SpringLiquibase configureLiquibase(DataSource dataSource,LiquibaseProperties liquibaseProperties){
        SpringLiquibase springLiquibase=new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog(liquibaseProperties.getChangeLog());
        return springLiquibase;

    }

}