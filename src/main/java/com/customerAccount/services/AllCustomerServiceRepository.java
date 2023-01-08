package com.customerAccount.services;


import java.util.List;
import com.customerAccount.services.dto.CustomerDTO;
import com.customerAccount.services.dto.ReportDTO;
import com.customerAccount.services.dto.RequestReportDTO;


public interface AllCustomerServiceRepository {
	
	public CustomerDTO withCustomerId(String customerId) throws Exception;
	
	public List<CustomerDTO> save(List<CustomerDTO> customerDTOlist) throws Exception;
	
	List<ReportDTO> getCustomerToReports(RequestReportDTO requestReportDTO) throws Exception;
}
