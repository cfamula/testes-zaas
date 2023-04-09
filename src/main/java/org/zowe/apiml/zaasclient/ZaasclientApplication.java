package org.zowe.apiml.zaasclient;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.zowe.apiml.zaasclient.application.GetToken;
import org.zowe.apiml.zaasclient.exception.ZaasClientException;


@SpringBootApplication
public class ZaasclientApplication {

	public static void main(String[] args) throws IOException, ZaasClientException {
		SpringApplication.run(ZaasclientApplication.class, args);

		Scanner sc = new Scanner(System.in);

		String userid;
		String passwd;
		String lgn;
		String ptkt;
		String appid;

		System.out.println("Entre com o userid: ");
		userid = sc.next();

		System.out.println("Entre com a senha: ");
		passwd = sc.next();

		System.out.println("Entre com a aplicacao: ");
		appid = sc.next();

		GetToken tk = new GetToken();

		lgn = tk.login(userid, passwd);
		
		System.out.println("Token = " + lgn);

		ptkt = tk.passticket(lgn, appid);

		System.out.println("Passticket = " + ptkt);

		sc.close();
	}

}
