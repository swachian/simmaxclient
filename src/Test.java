import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.sanss.restclient.RestClient;
import com.sanss.restclient.RestResponse;

public class Test {

	
	public static void main(String[] argvs) throws HttpException, IOException {
		ArrayList list = new ArrayList();
		String url = "http://192.168.202.72:8080/simmax/st/getNormalList/";
		RestClient restClient = new RestClient();
		NameValuePair np1 = new NameValuePair("numbers", "aaaaa");
		NameValuePair np2 = new NameValuePair("numbers", "bbbbb");
		list.add(np1);
		list.add(np2);
		for (int i = 0; i < 100; i++) {
			RestResponse response = restClient.post(url, list);
			System.out.println(response.getBody());
		}

	}
}
