package bill.boottest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@SpringBootApplication
public class App implements CommandLineRunner {
	private static Logger LOG = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@SqsListener("bill-poc")
	public void listen(DataObject message) {
		LOG.info("!!!! received message {} {}", message.getFoo(), message.getBar());
	}

	@Override
	public void run(String... args) throws Exception {
	}
	
	public static class DataObject {
		private String foo;
		private String bar;

		@JsonCreator
		public DataObject(@JsonProperty("foo") String foo, @JsonProperty("bar") String bar) {
			this.foo = foo;
			this.bar = bar;
		}

		public String getFoo() {
			return foo;
		}

		public String getBar() {
			return bar;
		}
	}
}
