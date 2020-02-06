package ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.User;

@Stateless(mappedName="UserEJB")
@LocalBean
public class UserEJB {

	@PersistenceContext
	EntityManager em;
	
	public void saveUser(User user) {
		em.merge(user);
	}
	
	public void deleteUser(int id) {
		TypedQuery<User> query = em.createQuery("DELETE FROM User u WHERE u.id = :id", User.class);
		query.setParameter("id", id).executeUpdate();
	}
	
	public List<User> getAll() {
		return em.createQuery("SELECT u FROM User u").getResultList();
	}
}
