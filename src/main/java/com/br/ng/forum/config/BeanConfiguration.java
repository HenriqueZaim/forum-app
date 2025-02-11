package com.br.ng.forum.config;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.format.support.FormattingConversionService;

@Configuration
public class BeanConfiguration {

    @Value("${aws.access_key_id}")
    private String accessKey;

    @Value("${aws.secret_access_key}")
    private String secretKey;

    @Value("${s3.region}")
    private String region;
    
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public DomainClassConverter<FormattingConversionService> domainClassConverter(FormattingConversionService conversionService){
        return new DomainClassConverter<FormattingConversionService>(conversionService);
    }

    @Bean
    public AmazonS3 s3Client(){
        BasicAWSCredentials awsCred = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
            .withRegion(Regions.fromName(region))
            .withCredentials(new AWSStaticCredentialsProvider(awsCred)).build();

        return s3Client;
    }
    
}

