package nu.danielsundberg.persistence.haddock;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import nu.danielsundberg.persistence.AbstractPersistenceJPATest;

import org.junit.Test;

public class EntityTest extends AbstractPersistenceJPATest {
	
	@Test
	public void testEntity() {
		new TestEntity();
	}
	
	@Entity
	@Table(name="TEST")
	public class TestEntity implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		@Id
		@SequenceGenerator(name="testGenerator", sequenceName="TESTSEQ")
		@GeneratedValue(generator="testGenerator")
		@Column(name="TESTID")
		private long id;

		public void setId(long id) {
			this.id = id;
		}

		public long getId() {
			return id;
		}
	}
	
}
