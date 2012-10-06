package br.com.vpsa.oauth2android;

import java.io.IOException;

import br.com.vpsa.oauth2android.exception.InvalidClientException;
import br.com.vpsa.oauth2android.exception.InvalidGrantException;
import br.com.vpsa.oauth2android.exception.InvalidRequestException;
import br.com.vpsa.oauth2android.exception.InvalidScopeException;
import br.com.vpsa.oauth2android.exception.InvalidTokenTypeException;
import br.com.vpsa.oauth2android.exception.OAuthException;
import br.com.vpsa.oauth2android.exception.UnauthorizedClientException;
import br.com.vpsa.oauth2android.exception.UnsupportedGrantTypeException;
import br.com.vpsa.oauth2android.response.Response;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class LoginActivity extends Activity {

    private String APP_ID = "50532a8770a7df0fbc000007";
	private String APP_SECRET = "f77b73fa34bd3166f0a45daf6efd143b3a623f0ade0c6a9c48f21f9ff855cf3e";
	private String REDIRECT_URI = "https://vpsatrends.herokuapp.com/";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Client cliente = new Client(APP_ID, APP_SECRET, REDIRECT_URI);
        OAuth oAuth = new OAuth(cliente);
//        oAuth.addScope( "photos" );
//        oAuth.addScope( "friends" );
//        oAuth.addScope( "private" );
//        oAuth.addScope( "public" );

        // request Authorization
        String authorizationUri =	oAuth.returnAuthorizationRequestUri();
        Uri uri = Uri.parse( authorizationUri );
        Intent intent = new Intent( Intent.ACTION_VIEW, uri );
        startActivity( intent );

        // provide text input field for authorization code here

//         // request access Token
//         List<NameValuePair> parameter = new ArrayList<NameValuePair>();
//         parameter.add(new BasicNameValuePair("photo", "1.jpg"));
//         String requestUri = "/request";
//
        Response response;
		try {
			response = oAuth.executeProtectedResourceRequest( "https://www.vpsa.com.br/apps/api/entidades", null );
			String result =  response.getResponseString();
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


         // do something with the resulting String....
        
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }

    
}
