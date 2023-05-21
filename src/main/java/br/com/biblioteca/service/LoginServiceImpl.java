package br.com.biblioteca.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import br.com.biblioteca.dao.UsuarioDAO;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.utils.SenhaUtils;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	private static final String CLIENT_ID = "284685346472-gftq4n31ajd112du7mg42qt5a04v1ted.apps.googleusercontent.com";
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	@Override
	@Transactional
	public Usuario realizarLogin(String login, String senha) {
		return usuarioDAO.pesquisarUsuarioPorLoginSenha(login, SenhaUtils.convertStringToMd5(senha));
	}

	@Override
	@Transactional
	public Usuario realizarLoginGoogle(String login) {
		return usuarioDAO.pesquisarUsuarioPorLoginGoogle(login);
	}

	@Override
	public Usuario extractGoogleLoginInfo(String credential) throws GeneralSecurityException, IOException {
		Usuario usuario = new Usuario();
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(HTTP_TRANSPORT, new GsonFactory())
				// Specify the CLIENT_ID of the app that accesses the backend:
				.setAudience(Collections.singletonList(CLIENT_ID))
				// Or, if multiple clients access the backend:
				//.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
				.build();

		// (Receive idTokenString by HTTPS POST)
		String idTokenString = credential;

		GoogleIdToken idToken = verifier.verify(idTokenString);
		if (idToken != null) {
			GoogleIdToken.Payload payload = idToken.getPayload();

			// Print user identifier
			String userId = payload.getSubject();
			System.out.println("User ID: " + userId);

			// Get profile information from payload
			String email = payload.getEmail();
			boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			String name = (String) payload.get("name");
			String pictureUrl = (String) payload.get("picture");
			String locale = (String) payload.get("locale");
			String familyName = (String) payload.get("family_name");
			String givenName = (String) payload.get("given_name");

			usuario.setNome(name);
			usuario.setEmail(email);
			usuario.setLogin(givenName.concat(userId));

		} else {
			System.out.println("Invalid ID token.");
		}

		return usuario;
	}

}