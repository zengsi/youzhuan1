package cn.yutongjiaoyu.zhangwei.djzx.model;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 	* A data access object (DAO) providing persistence and search support for Bumen entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.yutongjiaoyu.zhangwei.djzx.model.Bumen
  * @author MyEclipse Persistence Tools 
 */
public class BumenDAO extends BaseHibernateDAO  {
		 private static final Log log = LogFactory.getLog(BumenDAO.class);
		//property constants
	public static final String BUMENMING = "bumenming";
	public static final String BUMENJIESHAO = "bumenjieshao";



    
    public void save(Bumen transientInstance) {
        log.debug("saving Bumen instance");
    	Transaction tr=getSession().beginTransaction();
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
        tr.commit();
    }
    
	public void delete(Bumen persistentInstance) {
        log.debug("deleting Bumen instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Bumen findById( java.lang.Integer id) {
        log.debug("getting Bumen instance with id: " + id);
        try {
            Bumen instance = (Bumen) getSession()
                    .get("cn.yutongjiaoyu.zhangwei.djzx.model.Bumen", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Bumen instance) {
        log.debug("finding Bumen instance by example");
        try {
            List results = getSession()
                    .createCriteria("cn.yutongjiaoyu.zhangwei.djzx.model.Bumen")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Bumen instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Bumen as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByBumenming(Object bumenming
	) {
		return findByProperty(BUMENMING, bumenming
		);
	}
	
	public List findByBumenjieshao(Object bumenjieshao
	) {
		return findByProperty(BUMENJIESHAO, bumenjieshao
		);
	}
	

	public List findAll() {
		log.debug("finding all Bumen instances");
		try {
			String queryString = "from Bumen";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Bumen merge(Bumen detachedInstance) {
        log.debug("merging Bumen instance");
        try {
            Bumen result = (Bumen) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Bumen instance) {
        log.debug("attaching dirty Bumen instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Bumen instance) {
        log.debug("attaching clean Bumen instance");
        try {
                      	getSession().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}