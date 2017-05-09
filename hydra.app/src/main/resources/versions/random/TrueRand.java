package versions.rand; // versions.[algorithm name]

import com.github.noxchimaera.zmok;
import akka.dispatch.Futures;
import akka.japi.Option;
import scala.concurrent.Future;

// public class [version name] implements GenVersion<[version result type]>
public class TrueRand implements GenVersion<Integer> { 
	@Override
	public Future<Integer> getHeuristicAsync() {
		return Futures.successful(4);
	}

	@Override
	public Option<Integer> getHeuristic() {
		return Option.option(4);
	}
}
