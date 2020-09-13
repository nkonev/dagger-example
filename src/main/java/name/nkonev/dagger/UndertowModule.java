package name.nkonev.dagger;

import dagger.Module;
import dagger.Provides;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;
import io.undertow.util.Headers;
import javax.inject.Singleton;

@Module
public class UndertowModule {

  private static final HttpHandler ROUTES = new RoutingHandler()
      .get("/", new ConstantStringHandler())
      .setFallbackHandler(UndertowModule::notFoundHandler);

  @Provides @Singleton public Undertow configureUndertow() {
    return Undertow.builder()
        .addHttpListener(8080, "localhost")
        .setHandler(ROUTES).build();
  }

  public static void notFoundHandler(HttpServerExchange exchange) {
    exchange.setStatusCode(404);
    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
    exchange.getResponseSender().send("Page Not Found!!");
  }
}

class ConstantStringHandler implements HttpHandler {
  @Override
  public void handleRequest(HttpServerExchange exchange) throws Exception {
    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
    exchange.getResponseSender().send("Hello, dude\n");
  }
}