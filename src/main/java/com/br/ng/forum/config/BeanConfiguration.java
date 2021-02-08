package com.br.ng.forum.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.Region;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.format.support.FormattingConversionService;

@Configuration
public class BeanConfiguration {
    
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public DomainClassConverter<FormattingConversionService> domainClassConverter(FormattingConversionService conversionService){
        return new DomainClassConverter<FormattingConversionService>(conversionService);
    }

    // @Bean
    // public AmazonS3 s3NinjaMock(){
    //     AWSCredentials credentials = 
    //         new BasicAWSCredentials("AKIAIOSFODNN7EXAMPLE", "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY");
    //     AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
    //         .withRegion(Regions.SA_EAST_1)
    //         .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

    //     return s3Client;
    // }

    }

