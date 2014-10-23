package com.jcwx.game.common.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jasypt.util.text.BasicTextEncryptor;

public class KeyEncryptTool {
    public static void main(String[] args) {
	BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
	textEncryptor.setPassword(MasterKeyUtil.getMasterKey());

	System.out.print("Please Input Key:");

	BufferedReader d = new BufferedReader(new InputStreamReader(System.in));
	try {
	    String line;
	    while ((line = d.readLine()).length() != 0) {
		if (line.length() == 0)
		    continue;
		String newPassword = textEncryptor.encrypt(line);
		// String new2Password = textEncryptor.decrypt(line);

		System.out.println("Encrypted en Key:" + newPassword);
		// System.out.println("Encrypted de Key:" + new2Password);

		break;
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
