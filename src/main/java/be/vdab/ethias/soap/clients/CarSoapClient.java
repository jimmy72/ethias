package be.vdab.ethias.soap.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.WebServiceTransportException;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.SoapFaultClientException;

import be.vdab.ethias.exceptions.CarClientTransportException;
import be.vdab.ethias.wsdl.GetCarRequest;
import be.vdab.ethias.wsdl.GetCarResponse;

public class CarSoapClient extends WebServiceGatewaySupport {
	private static final Logger log = LoggerFactory.getLogger(CarSoapClient.class);

	public GetCarResponse getCarResponse(String brand, String model) {

		GetCarRequest carRequest = new GetCarRequest();
		carRequest.setBrand(brand);
		carRequest.setModel(model);

		log.info("Requesting price for " + brand + " " + model);
		try {
			GetCarResponse carResponse = (GetCarResponse) getWebServiceTemplate().marshalSendAndReceive("http://localhost:8080/ws", carRequest);
			return carResponse;
		}catch(SoapFaultClientException ex) {
			throw new CarClientTransportException(ex.getMessage() + ": Car soap request succeeded but car was not found!!!");
		}
		catch(WebServiceTransportException ex) {
			throw new CarClientTransportException(ex.getMessage() + ": Car soap request did not succeed, connection problems!!!");
		}
	}

}
