/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.springboot.web;

import com.example.springboot.domain.Review;
import com.example.springboot.service.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@Autowired
	private ReviewRepository reviewRepository;

	@RequestMapping(value = "/review/get",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@Transactional(readOnly = true)
	public String getReview(@RequestParam Long id){
		if(id != null && id.longValue() > 0){
			Review review = reviewRepository.findOne(id);
			return review.toString();
		}else{
			return "NULL";
		}
	}

	@RequestMapping(value = "/review/set",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	@Transactional
	public String setReview(@RequestParam(defaultValue = "0") Long id,
							@RequestParam(defaultValue = "") String details){
		if(id != null && id.longValue() > 0){
			Review review = reviewRepository.findOne(id);
			if(review == null){
				return "NULL";
			}else{
				review.setDetails(details);
				reviewRepository.save(review);
			}
			return review.toString();
		}else{
			return "NULL";
		}
	}
}
