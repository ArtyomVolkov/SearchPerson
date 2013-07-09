package volkov.search_person.service;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import org.springframework.stereotype.Service;
import volkov.search_person.model.User;

/**
 * Manages searching  persons
 */
@Service
public class SearchPersonService {

	private static final String URL = "ldap://localhost:389/ou=People,dc=us,dc=com";
	private static final String LdapCtxFactory = "com.sun.jndi.ldap.LdapCtxFactory";
	private DirContext ctx ;
	private  NamingEnumeration<?> results = null;
	
	/**
	 * Search  users names in LDAP
	 */
	public List<String> getFullNameUserDS() throws javax.naming.NamingException {

		List<String> list = new LinkedList<String>();
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, LdapCtxFactory);
		env.put(Context.PROVIDER_URL, URL);
		try {
			ctx = new InitialDirContext(env);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
		try {
			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			results = ctx.search("", "(objectclass=person)", controls);
			while (results.hasMore()) {
				SearchResult searchResult = (SearchResult) results.next();
				Attributes attributes = searchResult.getAttributes();
				if (attributes.get("givenName") != null) {
					list.add((String) attributes.get("givenName").get() + " " + (String) attributes.get("sn").get());
				}
			}	

		} catch (NameNotFoundException e) {
			System.err.println("Error : " + e);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		} finally {
			if (results != null) {
				try {
					results.close();
				} catch (Exception e) {
					System.out.println("Error : " + e);
				}
			}
			if (ctx != null) {
				try {
					ctx.close();
				} catch (Exception e) {
					System.out.println("Error : " + e);
				}
			}
		}
		return list;
	}
	
	/**
	 * Search uid by full user name
	 */
	public String findUidByFullName(String fullUserName) throws javax.naming.NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();
		
		env.put(Context.INITIAL_CONTEXT_FACTORY, LdapCtxFactory);
		env.put(Context.PROVIDER_URL, URL);
		try {
			ctx = new InitialDirContext(env);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
		String name = null;
		try {
			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			results = ctx.search("", "(objectclass=person)", controls);
			while (results.hasMore()) {
				SearchResult searchResult = (SearchResult) results.next();
				Attributes attributes = searchResult.getAttributes();
				String UserName = attributes.get("givenName").get() + " "+ attributes.get("sn").get();
				if (fullUserName.equals(UserName)){
					name = (String) attributes.get("uid").get();
				}
			}
		} catch (NameNotFoundException e) {
			System.out.println("Error : " + e);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		} finally {
			if (results != null) {
				try {
					results.close();
				} catch (Exception e) {
					System.out.println("Error : " + e);
				}
			}
			if (ctx != null) {
				try {
					ctx.close();
				} catch (Exception e) {
					System.out.println("Error : " + e);
				}
			}
		}
		return name;
	}
	
	/**
	 * Search user in LDAP by him uid
	 */
	public User getUser (String userName) throws javax.naming.NamingException {

		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, LdapCtxFactory);
		env.put(Context.PROVIDER_URL,
				"ldap://localhost:389/uid=" + userName + ",ou=People,dc=us,dc=com");
		try {
			ctx = new InitialDirContext(env);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
		User user = new User();
		try {
			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			results = ctx.search("", "(objectclass=person)", controls);
			while (results.hasMore()) {
				SearchResult searchResult = (SearchResult) results.next();
				Attributes attributes = searchResult.getAttributes();
				if (attributes.get("givenName") != null) {
					user.setFirstName((String) attributes.get("givenName").get());
					user.setLastName((String) attributes.get("sn").get());
					user.setEmail((String) attributes.get("mail").get());
				}
			}	

		} catch (NameNotFoundException e) {
			System.err.println("Error : " + e);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		} finally {
			if (results != null) {
				try {
					results.close();
				} catch (Exception e) {
					System.out.println("Error : " + e);
				}
			}
			if (ctx != null) {
				try {
					ctx.close();
				} catch (Exception e) {
					System.out.println("Error : " + e);
				}
			}
		}
		return user;
	}
}
