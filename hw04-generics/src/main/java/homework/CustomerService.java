package homework;

import java.util.AbstractMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CustomerService {

    private final NavigableMap<Customer, String> customers
            = new TreeMap<>((c1, c2) -> (int) (c1.getScores() - c2.getScores()));

    public Map.Entry<Customer, String> getSmallest() {
        return copyEntry(customers.firstEntry());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return copyEntry(customers.higherEntry(customer));
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }

    private Map.Entry<Customer, String> copyEntry(Map.Entry<Customer, String> entry) {
        if (null == entry) {
            return null;
        }
        Customer oldCustomer = entry.getKey();
        String oldValue = entry.getValue();
        Customer copyCustomer = new Customer(oldCustomer.getId(), oldCustomer.getName(), oldCustomer.getScores());
        return new AbstractMap.SimpleImmutableEntry<>(copyCustomer, oldValue);

    }
}
