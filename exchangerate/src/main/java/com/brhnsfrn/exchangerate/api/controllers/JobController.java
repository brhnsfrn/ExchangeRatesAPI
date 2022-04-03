package com.brhnsfrn.exchangerate.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brhnsfrn.exchangerate.business.abstracts.JobService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/job")
public class JobController {

	private JobService jobService;

	@Autowired
	public JobController(JobService jobService) {
		super();
		this.jobService = jobService;
	}
	
	@GetMapping("/execute")
	@ApiOperation("Data is retrieved manually from Central Bank of Turkey service.")
	public ResponseEntity<?> execute(){
		return ResponseEntity.ok(this.jobService.getData());
	}
	
}
