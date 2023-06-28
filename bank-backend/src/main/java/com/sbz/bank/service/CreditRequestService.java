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

	public List<CreditRequest> getByClient(Long id) {
		return creditRequestRepository.findByClient(id);
	}

	public CreditRequest createCreditRequest(CreditRequest creditRequest) {
		KieContainer kieContainer = new KieConfig().kieContainer();
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(creditRequest);
		kieSession.fireAllRules();

		return creditRequestRepository.save(creditRequest);
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
