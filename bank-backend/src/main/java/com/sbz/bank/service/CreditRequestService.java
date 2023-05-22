package com.sbz.bank.service;

import com.sbz.bank.config.KieConfig;
import com.sbz.bank.model.CreditRequest;
import com.sbz.bank.repository.CreditRequestRepository;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditRequestService {
	@Autowired
	private CreditRequestRepository creditRequestRepository;

	public List<CreditRequest> getAll() {
		return creditRequestRepository.findAll();
	}

	public CreditRequest getById(Long id) {
		return creditRequestRepository.findById(id).get();
	}

	public CreditRequest createCreditRequest(CreditRequest creditRequest) {
		CreditRequest request = creditRequestRepository.save(creditRequest);
		KieContainer kieContainer = new KieConfig().kieContainer();
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(request);

		return request;
	}

	public CreditRequest updateCreditRequest(CreditRequest creditRequest) {
		return creditRequestRepository.save(creditRequest);
	}

	public boolean deleteCreditRequest(Long id) {
		if (creditRequestRepository.existsById(id)) {
			creditRequestRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
