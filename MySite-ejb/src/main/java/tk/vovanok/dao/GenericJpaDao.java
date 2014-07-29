package tk.vovanok.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import tk.vovanok.entities.BaseEntity;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */


public abstract class GenericJpaDao<T, ID extends Serializable> 
                                  implements GenericDao<T, ID>{

    private Class<T> persistantClass;
    
    @PersistenceContext(unitName = "jpaLayer")
    private EntityManager entityManager;

    public GenericJpaDao() {
        this.persistantClass = (Class<T>) ((ParameterizedType) getClass()  
                                .getGenericSuperclass()).getActualTypeArguments()[0];  
    }
    
    
    @Override
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        T mergedEntity = entityManager.merge(entity);
        return mergedEntity;
    }

    @Override
    public void delete(T entity) {
        if(BaseEntity.class.isAssignableFrom(persistantClass)){
            entityManager.remove(entityManager.getReference(persistantClass, ((BaseEntity)entity).getId()));
        } else {
            entity = entityManager.merge(entity);
            entityManager.remove(entity);
        }
    }

    @Override
    public T findById(ID id) {
        T entity = (T) entityManager.find(persistantClass, id);
        return entity;
    }

    @Override
    public void delete(ID id) {
        T entity = (T) entityManager.find(persistantClass, id);
        if(entity != null) delete(entity);
    }
    
    @Override
    public List<T> findAll() {
        return entityManager.createQuery("SELECT x FROM "+persistantClass.getSimpleName()+" x").getResultList();
    }

    @Override
    public Long countAll() {
        Query q = getEntityManager().createQuery("select count(*) from "
                                                    +getPersistantClass().getSimpleName()
                                                    +" c ");
        Long count =(Long) q.getSingleResult();
        return count;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Class<T> getPersistantClass() {
        return persistantClass;
    }
   
    
    
}
