package de.jugsaar.meeting8.testing.junit.awaitility;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.mockito.Mockito.when;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.LockSupport;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Thomas Darimont
 * @see https://code.google.com/p/awaitility/
 */
@RunWith(MockitoJUnitRunner.class)
public class AwatilityExample {

	static interface CrmSystem {
		void publishEvent(CustomerEvent event);

		Callable<Boolean> costumerStatusIsUpdated(Customer customer);
	};

	static interface Customer {};

	static interface CustomerEvent {
		Customer getCustomer();
	}

	@Mock CrmSystem crm;

	@Mock Customer customer;

	@Mock CustomerEvent event;

	@Test
	public void updatesCustomerStatus() throws Exception {

		Callable<Boolean> updatePredicate = new Callable<Boolean>() {

			int calls = 0;

			@Override
			public Boolean call() throws Exception {

				System.out.println("Call: " + calls);

				LockSupport.parkNanos(SECONDS.toNanos(1));
				return ++calls > 2;
			}
		};

		when(event.getCustomer()).thenReturn(customer);
		when(crm.costumerStatusIsUpdated(customer)).thenReturn(updatePredicate);

		crm.publishEvent(event);

		// Awaitility lets you wait until the asynchronous operation completes:
		await().atMost(2, SECONDS).until(crm.costumerStatusIsUpdated(customer));
	}
}
