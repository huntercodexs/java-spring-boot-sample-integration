# java-spring-boot-sample-integration
Simple Java Spring Boot Application to demonstrate integration with various tools and services.

> Hint: Use the docker containers configurations placed in github account: docker-series 
> https://github.com/huntercodexs/docker-series/tree/master/self-containers/messenger for (PLAINTEXT and SASL_PLAINTEXT)

## Tests

- Integration
  - Feign Requests
    - 200 OK Response
    - 500 Internal Server Error Response
    - 503 Service Unavailable Response
  - Feign Receives
  - Feign Retry
  - Feign Client Interceptor
- Circuit Breaker
- Exception Handling
- Rate Limit
  - Rate Limit Exception Handling
- Rate Limit Service Bus
  - Rate Limit Service Bus Exception Handling
- Mongo DB
  - MongoDB Integration
  - MongoDB Exception Handling
  - MongoDB Retry

### Integration Tests

- 200 OK Response
```bash
curl -X GET http://localhost:8080/api/users/test/success
```
Response is similar to:
```text
200 OK {}
```
Results in logs similar to:
```text
>>> success start
>>> Starting integration request
UserRequestSimulation(name=Username Test, email=username@email.com)
2025-11-22 14:18:32.145 [http-nio-8080-exec-1] [INFO ] [SLT-003751] c.h.i.c.c.IntegrationClientInterceptorConfig.retrieveClientToken - No interceptor found for target: user
2025-11-22 14:18:32.155 [http-nio-8080-exec-1] [INFO ] [SLT-003751] c.h.i.c.logger.IntegrationHttpLogger.logRequest - Request sent - method: POST | url: http://localhost:8080/api/users/create | headers: {Content-Length=53, Content-Type=application/json} | body: {"name":"Username Test","email":"username@email.com"}
>>> create start
UserRequestSimulation(name=Username Test, email=username@email.com)
>>> finished
2025-11-22 14:18:32.273 [http-nio-8080-exec-1] [INFO ] [SLT-003751] c.h.i.c.logger.IntegrationHttpLogger.logAndRebufferResponse - Request received - status: 200 | elapsedTime: 118ms | headers: {date=Sat, 22 Nov 2025 17:18:32 GMT, content-length=0, keep-alive=timeout=60, connection=keep-alive} | body: 
>>> The result is: null
>>> finished
```
*********************************************************************************************************

- 500 Internal Server Error Response
```bash
curl -X GET http://localhost:8080/api/users/test/exception
```
Response is similar to:
```json
{
  "message": "Internal server error",
  "timestamp": "2025-11-22T14:21:57.8879546",
  "code": "500",
  "tracker": "b9c0472f-e3f8-4913-9ae8-dbe42962bf2f",
  "errors": [
    "ResponseStatusException: 500 INTERNAL_SERVER_ERROR \"Feign UserApiClientSimulation#create(UserRequestSimulation) failed with status 500 : {\"message\":\"Internal server error\",\"timestamp\":\"2025-11-22T14:21:57.8602832\",\"code\":\"500\",\"tracker\":\"7b7907e2-2b36-46c6-a79b-961e65f2c701\",\"errors\":[\"RuntimeException: Email is mandatory\"]}\""
  ]
}
```
Results in logs similar to:
```text
>>> exception start
>>> Starting integration request
UserRequestSimulation(name=Username Test, email=null)
2025-11-22 14:21:57.853 [http-nio-8080-exec-6] [INFO ] [SLT-003751] c.h.i.c.c.IntegrationClientInterceptorConfig.retrieveClientToken - No interceptor found for target: user
2025-11-22 14:21:57.853 [http-nio-8080-exec-6] [INFO ] [SLT-003751] c.h.i.c.logger.IntegrationHttpLogger.logRequest - Request sent - method: POST | url: http://localhost:8080/api/users/create | headers: {Content-Length=24, Content-Type=application/json} | body: {"name":"Username Test"}
>>> create start
UserRequestSimulation(name=Username Test, email=null)
>>> Email is null, throwing exception
2025-11-22 14:21:57.860 [http-nio-8080-exec-7] [ERROR] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.handleRuntimeException - Unhandled runtime exception
java.lang.RuntimeException: Email is mandatory
	at com.huntercodexs.sample.controlller.UserControllerSimulation.create(UserControllerSimulation.java:76)
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)
	at java.base/java.lang.reflect.Method.invoke(Method.java:586)
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:255)
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:188)
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:118)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:926)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:831)
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1089)
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:979)
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014)
	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:914)
	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885)
	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:195)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
	at org.springframework.web.filter.ServerHttpObservationFilter.doFilterInternal(ServerHttpObservationFilter.java:113)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:483)
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:116)
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:344)
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:398)
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:903)
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1769)
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)
	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1189)
	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:658)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63)
	at java.base/java.lang.Thread.run(Thread.java:1595)
2025-11-22 14:21:57.860 [http-nio-8080-exec-7] [INFO ] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.buildErrorResponse - No tracker provided; generated automatically: 7b7907e2-2b36-46c6-a79b-961e65f2c701
2025-11-22 14:21:57.860 [http-nio-8080-exec-7] [ERROR] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.logException - [7b7907e2-2b36-46c6-a79b-961e65f2c701] 500 INTERNAL_SERVER_ERROR - Internal server error | errors=[RuntimeException: Email is mandatory]
2025-11-22 14:21:57.882 [http-nio-8080-exec-6] [INFO ] [SLT-003751] c.h.i.c.logger.IntegrationHttpLogger.logAndRebufferResponse - Request received - status: 500 | elapsedTime: 27ms | headers: {date=Sat, 22 Nov 2025 17:21:57 GMT, transfer-encoding=chunked, connection=close, content-type=application/json} | body: {"message":"Internal server error","timestamp":"2025-11-22T14:21:57.8602832","code":"500","tracker":"7b7907e2-2b36-46c6-a79b-961e65f2c701","errors":["RuntimeException: Email is mandatory"]}
2025-11-22 14:21:57.886 [http-nio-8080-exec-6] [ERROR] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.handleRuntimeException - Unhandled runtime exception
org.springframework.web.server.ResponseStatusException: 500 INTERNAL_SERVER_ERROR "Feign UserApiClientSimulation#create(UserRequestSimulation) failed with status 500 : {"message":"Internal server error","timestamp":"2025-11-22T14:21:57.8602832","code":"500","tracker":"7b7907e2-2b36-46c6-a79b-961e65f2c701","errors":["RuntimeException: Email is mandatory"]}"
	at com.huntercodexs.integration.core.decoder.IntegrationErrorDecoder.decode(IntegrationErrorDecoder.java:34)
	at feign.InvocationContext.decodeError(InvocationContext.java:126)
	at feign.InvocationContext.proceed(InvocationContext.java:72)
	at feign.ResponseHandler.handleResponse(ResponseHandler.java:63)
	at feign.SynchronousMethodHandler.executeAndDecode(SynchronousMethodHandler.java:114)
	at feign.SynchronousMethodHandler.invoke(SynchronousMethodHandler.java:70)
	at feign.ReflectiveFeign$FeignInvocationHandler.invoke(ReflectiveFeign.java:99)
	at jdk.proxy2/jdk.proxy2.$Proxy115.create(Unknown Source)
	at com.huntercodexs.sample.controlller.UserControllerSimulation.exception(UserControllerSimulation.java:46)
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)
	at java.base/java.lang.reflect.Method.invoke(Method.java:586)
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:255)
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:188)
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:118)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:926)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:831)
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1089)
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:979)
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014)
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:903)
	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885)
	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:195)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
	at org.springframework.web.filter.ServerHttpObservationFilter.doFilterInternal(ServerHttpObservationFilter.java:113)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:483)
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:116)
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:344)
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:398)
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:903)
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1769)
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)
	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1189)
	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:658)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63)
	at java.base/java.lang.Thread.run(Thread.java:1595)
2025-11-22 14:21:57.887 [http-nio-8080-exec-6] [INFO ] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.buildErrorResponse - No tracker provided; generated automatically: b9c0472f-e3f8-4913-9ae8-dbe42962bf2f
2025-11-22 14:21:57.887 [http-nio-8080-exec-6] [ERROR] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.logException - [b9c0472f-e3f8-4913-9ae8-dbe42962bf2f] 500 INTERNAL_SERVER_ERROR - Internal server error | errors=[ResponseStatusException: 500 INTERNAL_SERVER_ERROR "Feign UserApiClientSimulation#create(UserRequestSimulation) failed with status 500 : {"message":"Internal server error","timestamp":"2025-11-22T14:21:57.8602832","code":"500","tracker":"7b7907e2-2b36-46c6-a79b-961e65f2c701","errors":["RuntimeException: Email is mandatory"]}"]
```
If the interface IntegrationGlobalExceptionInterceptor is implemented, the error can be customized globally, for example:
```java
@Component
public class Impl3 implements IntegrationGlobalExceptionInterceptor {
    @Override
    public boolean supports(IntegrationGlobalEnum value) {
        return value.equals(RUNTIME_EXCEPTION_INTERCEPTOR_500);
    }

    @Override
    public String message() {
        return "Mensagem";
    }

    @Override
    public String trackerId() {
        return "8329083290";
    }

    @Override
    public String code() {
        return "3";
    }

    @Override
    public List<String> errors(Object exception) {
        return List.of("Erro 1", "Erro 2");
    }
}
```
The response will be:
```json
{
  "message": "Mensagem",
  "timestamp": "2025-11-22T14:30:12.3456789",
  "code": "3",
  "tracker": "8329083290",
  "errors": [
    "Erro 1",
    "Erro 2"
  ]
}
```

*********************************************************************************************************

- 503 Service Unavailable Response
```bash
curl -X GET http://localhost:8080/api/users/test/invalid-api
```
Response is similar to:
```json
{
  "message": "Limit of requests exceeded for Integration",
  "timestamp": "2025-11-22T15:53:34.3974471",
  "code": "503",
  "tracker": "1f179f5b-cbc1-4586-9644-53ad13609279",
  "errors": [
    "Integration Retries Exceeded: 2"
  ]
}
```
Results in logs similar to:
```text
>>> invalid-api start
>>> Starting integration request
UserRequestSimulation(name=Username Test, email=username@email.com)
2025-11-22 15:53:32.335 [http-nio-8080-exec-1] [INFO ] [SLT-003751] c.h.i.c.c.IntegrationClientInterceptorConfig.retrieveClientToken - No interceptor found for target: invalid-api
2025-11-22 15:53:32.338 [http-nio-8080-exec-1] [INFO ] [SLT-003751] c.h.i.c.logger.IntegrationHttpLogger.logRequest - Request sent - method: POST | url: http://localhost:8085/api/users/create | headers: {Content-Length=53, Content-Type=application/json} | body: {"name":"Username Test","email":"username@email.com"}
2025-11-22 15:53:32.366 [http-nio-8080-exec-1] [INFO ] [SLT-003751] c.h.i.c.l.IntegrationRetryerLogger.continueOrPropagate - Retrying request - 1/2 | method: POST | url: http://localhost:8085/api/users/create | message: Connection refused: getsockopt executing POST http://localhost:8085/api/users/create
2025-11-22 15:53:34.371 [http-nio-8080-exec-1] [INFO ] [SLT-003751] c.h.i.c.c.IntegrationClientInterceptorConfig.retrieveClientToken - No interceptor found for target: invalid-api
2025-11-22 15:53:34.372 [http-nio-8080-exec-1] [INFO ] [SLT-003751] c.h.i.c.logger.IntegrationHttpLogger.logRequest - Request sent - method: POST | url: http://localhost:8085/api/users/create | headers: {Content-Length=53, Content-Type=application/json} | body: {"name":"Username Test","email":"username@email.com"}
2025-11-22 15:53:34.389 [http-nio-8080-exec-1] [WARN ] [SLT-003751] c.h.i.c.l.IntegrationRetryerLogger.continueOrPropagate - Limit of retries reached, (tries: 2) | method: POST | url: http://localhost:8085/api/users/create | message: Connection refused: getsockopt executing POST http://localhost:8085/api/users/create
2025-11-22 15:53:34.395 [http-nio-8080-exec-1] [ERROR] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.handleIntegrationRetryAttemptsExceededException - Limit of requests exceeded for Integration: Integration Retries Exceeded: 2
2025-11-22 15:53:34.396 [http-nio-8080-exec-1] [INFO ] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.buildErrorResponse - No tracker provided; generated automatically: 1f179f5b-cbc1-4586-9644-53ad13609279
2025-11-22 15:53:34.397 [http-nio-8080-exec-1] [ERROR] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.logException - [1f179f5b-cbc1-4586-9644-53ad13609279] 503 SERVICE_UNAVAILABLE - Limit of requests exceeded for Integration | errors=[Integration Retries Exceeded: 2]
```
Looks similar to the previous exception handling, but now for IntegrationRetryAttemptsExceededException, in this case after retrying the request the maximum number of times configured that was defined in two tries.

- Feign Interceptor
If a Feign Client Interceptor is defined for the target integration, for example:
```java
@Component
public class UserInterceptorImpl implements IntegrationClientInterceptor {

    @Autowired
    private UserManagerService userManagerService;

    @Override
    public boolean checkSupport(Object value) {
        return value.toString().equals("user");
    }

    @Override
    public String getClientToken() {
        return userManagerService.getClientToken().orElse("");
    }

    @Service
    public static class UserManagerService {
        public Optional<String> getClientToken() {
            System.out.println("calling getClientToken from UserManagerService");
            return Optional.of("Bearer UserManagerTokenFake");
        }
    }
}
```
Example of log output when calling the valid endpoint:
```text
>>> success start
>>> Starting integration request
UserRequestSimulation(name=Username Test, email=username@email.com)
calling getClientToken from UserManagerService
2025-11-22 16:31:04.954 [http-nio-8080-exec-1] [INFO ] [SLT-003751] c.h.i.c.c.IntegrationClientInterceptorConfig.retrieveClientToken - Interceptor applied for target: user, headers: {Authorization=[Bearer UserManagerTokenFake], Content-Length=[53], Content-Type=[application/json]}
2025-11-22 16:31:26.195 [http-nio-8080-exec-1] [INFO ] [SLT-003751] c.h.i.c.logger.IntegrationHttpLogger.logRequest - Request sent - method: POST | url: http://localhost:8080/api/users/create | headers: {Authorization=Bearer UserManagerTokenFake, Content-Length=53, Content-Type=application/json} | body: {"name":"Username Test","email":"username@email.com"}
>>> create start
UserRequestSimulation(name=Username Test, email=username@email.com)
>>> finished
2025-11-22 16:31:26.295 [http-nio-8080-exec-1] [INFO ] [SLT-003751] c.h.i.c.logger.IntegrationHttpLogger.logAndRebufferResponse - Request received - status: 200 | elapsedTime: 98ms | headers: {date=Sat, 22 Nov 2025 19:31:26 GMT, content-length=0, keep-alive=timeout=60, connection=keep-alive} | body: 
>>> The result is: null
>>> finished
```
In this case the Authorization header is added to the request with the token provided by the interceptor, as we can see in the logs.
```text
...headers: {Authorization=Bearer UserManagerTokenFake...
```

### Circuit Breaker Tests

- 503 Service Unavailable Response with Circuit Breaker Fallback
```bash
curl -X GET http://localhost:8080/api/users/test/invalid-api
```

> Note: In this case to activate the circuit breaker fallback, the invalid-api endpoint must be called multiple times until the circuit breaker opens.

The response is similar to:
```json
{
  "message": "Service is not available 'CircuitBreaker 'invalidApiClient' is OPEN and does not permit further calls'",
  "timestamp": "2025-11-22T16:08:30.7397699",
  "code": "503",
  "tracker": "eaaaac69-5cc2-4f47-864e-a94e5a4ec106",
  "errors": [
    "CircuitBreaker 'invalidApiClient' is OPEN and does not permit further calls",
    "invalidApiClient"
  ]
}
```
The logs are similar to:
```text
>>> invalid-api start
>>> Starting integration request
UserRequestSimulation(name=Username Test, email=username@email.com)
2025-11-22 16:13:05.934 [http-nio-8080-exec-1] [INFO ] [SLT-003751] c.h.i.c.c.IntegrationClientInterceptorConfig.retrieveClientToken - No interceptor found for target: invalid-api
2025-11-22 16:13:05.937 [http-nio-8080-exec-1] [INFO ] [SLT-003751] c.h.i.c.logger.IntegrationHttpLogger.logRequest - Request sent - method: POST | url: http://localhost:8085/api/users/create | headers: {Content-Length=53, Content-Type=application/json} | body: {"name":"Username Test","email":"username@email.com"}
2025-11-22 16:13:05.952 [http-nio-8080-exec-1] [INFO ] [SLT-003751] c.h.i.c.l.IntegrationRetryerLogger.continueOrPropagate - Retrying request - 1/2 | method: POST | url: http://localhost:8085/api/users/create | message: Connection refused: getsockopt executing POST http://localhost:8085/api/users/create
2025-11-22 16:13:07.968 [http-nio-8080-exec-1] [INFO ] [SLT-003751] c.h.i.c.c.IntegrationClientInterceptorConfig.retrieveClientToken - No interceptor found for target: invalid-api
2025-11-22 16:13:07.970 [http-nio-8080-exec-1] [INFO ] [SLT-003751] c.h.i.c.logger.IntegrationHttpLogger.logRequest - Request sent - method: POST | url: http://localhost:8085/api/users/create | headers: {Content-Length=53, Content-Type=application/json} | body: {"name":"Username Test","email":"username@email.com"}
2025-11-22 16:13:07.985 [http-nio-8080-exec-1] [WARN ] [SLT-003751] c.h.i.c.l.IntegrationRetryerLogger.continueOrPropagate - Limit of retries reached, (tries: 2) | method: POST | url: http://localhost:8085/api/users/create | message: Connection refused: getsockopt executing POST http://localhost:8085/api/users/create
2025-11-22 16:13:07.992 [http-nio-8080-exec-1] [ERROR] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.handleIntegrationRetryAttemptsExceededException - Limit of requests exceeded for Integration: Integration Retries Exceeded: 2
2025-11-22 16:13:07.992 [http-nio-8080-exec-1] [INFO ] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.buildErrorResponse - No tracker provided; generated automatically: 06d39f80-8a36-4da7-9604-2f9479b51dbe
2025-11-22 16:13:07.994 [http-nio-8080-exec-1] [ERROR] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.logException - [06d39f80-8a36-4da7-9604-2f9479b51dbe] 503 SERVICE_UNAVAILABLE - Limit of requests exceeded for Integration | errors=[Integration Retries Exceeded: 2]
>>> invalid-api start
>>> Starting integration request
UserRequestSimulation(name=Username Test, email=username@email.com)
2025-11-22 16:13:08.860 [http-nio-8080-exec-2] [INFO ] [SLT-003751] c.h.i.c.c.IntegrationClientInterceptorConfig.retrieveClientToken - No interceptor found for target: invalid-api
2025-11-22 16:13:08.860 [http-nio-8080-exec-2] [INFO ] [SLT-003751] c.h.i.c.logger.IntegrationHttpLogger.logRequest - Request sent - method: POST | url: http://localhost:8085/api/users/create | headers: {Content-Length=53, Content-Type=application/json} | body: {"name":"Username Test","email":"username@email.com"}
2025-11-22 16:13:08.878 [http-nio-8080-exec-2] [INFO ] [SLT-003751] c.h.i.c.l.IntegrationRetryerLogger.continueOrPropagate - Retrying request - 1/2 | method: POST | url: http://localhost:8085/api/users/create | message: Connection refused: getsockopt executing POST http://localhost:8085/api/users/create
2025-11-22 16:13:10.892 [http-nio-8080-exec-2] [INFO ] [SLT-003751] c.h.i.c.c.IntegrationClientInterceptorConfig.retrieveClientToken - No interceptor found for target: invalid-api
2025-11-22 16:13:10.894 [http-nio-8080-exec-2] [INFO ] [SLT-003751] c.h.i.c.logger.IntegrationHttpLogger.logRequest - Request sent - method: POST | url: http://localhost:8085/api/users/create | headers: {Content-Length=53, Content-Type=application/json} | body: {"name":"Username Test","email":"username@email.com"}
2025-11-22 16:13:10.899 [http-nio-8080-exec-2] [WARN ] [SLT-003751] c.h.i.c.l.IntegrationRetryerLogger.continueOrPropagate - Limit of retries reached, (tries: 2) | method: POST | url: http://localhost:8085/api/users/create | message: Connection refused: getsockopt executing POST http://localhost:8085/api/users/create
2025-11-22 16:13:10.899 [http-nio-8080-exec-2] [ERROR] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.handleIntegrationRetryAttemptsExceededException - Limit of requests exceeded for Integration: Integration Retries Exceeded: 2
2025-11-22 16:13:10.900 [http-nio-8080-exec-2] [INFO ] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.buildErrorResponse - No tracker provided; generated automatically: 38225018-d1d1-4442-94f6-a62d75d43d1e
2025-11-22 16:13:10.900 [http-nio-8080-exec-2] [ERROR] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.logException - [38225018-d1d1-4442-94f6-a62d75d43d1e] 503 SERVICE_UNAVAILABLE - Limit of requests exceeded for Integration | errors=[Integration Retries Exceeded: 2]
>>> invalid-api start
>>> Starting integration request
UserRequestSimulation(name=Username Test, email=username@email.com)
2025-11-22 16:13:11.310 [http-nio-8080-exec-3] [INFO ] [SLT-003751] c.h.i.c.c.IntegrationClientInterceptorConfig.retrieveClientToken - No interceptor found for target: invalid-api
2025-11-22 16:13:11.311 [http-nio-8080-exec-3] [INFO ] [SLT-003751] c.h.i.c.logger.IntegrationHttpLogger.logRequest - Request sent - method: POST | url: http://localhost:8085/api/users/create | headers: {Content-Length=53, Content-Type=application/json} | body: {"name":"Username Test","email":"username@email.com"}
2025-11-22 16:13:11.313 [http-nio-8080-exec-3] [INFO ] [SLT-003751] c.h.i.c.l.IntegrationRetryerLogger.continueOrPropagate - Retrying request - 1/2 | method: POST | url: http://localhost:8085/api/users/create | message: Connection refused: getsockopt executing POST http://localhost:8085/api/users/create
2025-11-22 16:13:13.327 [http-nio-8080-exec-3] [INFO ] [SLT-003751] c.h.i.c.c.IntegrationClientInterceptorConfig.retrieveClientToken - No interceptor found for target: invalid-api
2025-11-22 16:13:13.329 [http-nio-8080-exec-3] [INFO ] [SLT-003751] c.h.i.c.logger.IntegrationHttpLogger.logRequest - Request sent - method: POST | url: http://localhost:8085/api/users/create | headers: {Content-Length=53, Content-Type=application/json} | body: {"name":"Username Test","email":"username@email.com"}
2025-11-22 16:13:13.345 [http-nio-8080-exec-3] [WARN ] [SLT-003751] c.h.i.c.l.IntegrationRetryerLogger.continueOrPropagate - Limit of retries reached, (tries: 2) | method: POST | url: http://localhost:8085/api/users/create | message: Connection refused: getsockopt executing POST http://localhost:8085/api/users/create
2025-11-22 16:13:13.356 [http-nio-8080-exec-3] [ERROR] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.handleIntegrationRetryAttemptsExceededException - Limit of requests exceeded for Integration: Integration Retries Exceeded: 2
2025-11-22 16:13:13.357 [http-nio-8080-exec-3] [INFO ] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.buildErrorResponse - No tracker provided; generated automatically: 53842fcd-56ab-48d7-8224-c7a4d0b0489e
2025-11-22 16:13:13.357 [http-nio-8080-exec-3] [ERROR] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.logException - [53842fcd-56ab-48d7-8224-c7a4d0b0489e] 503 SERVICE_UNAVAILABLE - Limit of requests exceeded for Integration | errors=[Integration Retries Exceeded: 2]
>>> invalid-api start
>>> Starting integration request
UserRequestSimulation(name=Username Test, email=username@email.com)
2025-11-22 16:13:13.777 [http-nio-8080-exec-9] [ERROR] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.logException - [8329083290] 503 SERVICE_UNAVAILABLE - Mensagem | errors=[Erro 1, Erro 2, Erro 3]
```
It is possible to customize the circuit breaker fallback response by implementing the IntegrationCircuitBreakerFallbackInterceptor interface, for example:
```java
@Component
public class Impl4 implements IntegrationGlobalExceptionInterceptor {
    @Override
    public boolean supports(IntegrationGlobalEnum value) {
        return value.equals(CIRCUIT_BREAKER_CALL_NOT_PERMITTED_EXCEPTION_INTERCEPTOR_503);
    }

    @Override
    public String message() {
        return "Mensagem";
    }

    @Override
    public String trackerId() {
        return "8329083290";
    }

    @Override
    public String code() {
        return "4";
    }

    @Override
    public List<String> errors(Object exception) {
        return List.of("Erro 1", "Erro 2", "Erro 3");
    }
}
```
In this case the response should be:
```json
{
  "message": "Mensagem",
  "timestamp": "2025-11-22T16:13:13.777442",
  "code": "4",
  "tracker": "8329083290",
  "errors": [
    "Erro 1",
    "Erro 2",
    "Erro 3"
  ]
}
```

### Exception Handling Tests

The exception handling mechanism is already demonstrated in the previous sections, both for internal exceptions (500) and for integration exceptions (503).

### Rate Limit Tests

> IMPORTANT: Needs Redis running for rate limit tests.

- 200 OK Response
```bash
curl -X GET http://localhost:8080/api/limited
```
Using Postman or other tool, make multiple requests within the rate limit configured (3 requests per 10 seconds).

The response is similar to:
```text
200 OK Request Allowed. Limit: 3/10s.
```
If the request is made multiple times within the rate limit, it will be allowed until the limit is reached, and the 
logs will show entries like:
```text
2025-11-22 19:37:25.237 [http-nio-8080-exec-10] [INFO ] [SLT-003751] c.h.i.r.aspect.RateLimitAspect.rateLimit - Rate Limit Check - key: ratelimit-prefix-default:0:0:0:0:0:0:0:1:limitedEndpoint, count: 28, limit: 3/10 SECONDS
2025-11-22 19:37:25.238 [http-nio-8080-exec-10] [ERROR] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.handleRateLimitExceededException - Limit of requests exceeded. Please try again later.
com.huntercodexs.integration.handler.exception.RateLimitExceededException: Limit of 3 requests was exceeded in 10 seconds.
	at com.huntercodexs.integration.ratelimit.aspect.RateLimitAspect.limitExceededAction(RateLimitAspect.java:127)
	at com.huntercodexs.integration.ratelimit.aspect.RateLimitAspect.rateLimit(RateLimitAspect.java:119)
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)
	at java.base/java.lang.reflect.Method.invoke(Method.java:586)
	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:641)
	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:631)
	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:71)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:173)
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:769)
	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:769)
	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:721)
	at com.huntercodexs.sample.controlller.RateLimitControllerSimulation$$SpringCGLIB$$0.limitedEndpoint(<generated>)
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)
	at java.base/java.lang.reflect.Method.invoke(Method.java:586)
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:255)
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:188)
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:118)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:926)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:831)
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1089)
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:979)
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014)
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:903)
	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885)
	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:195)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
	at org.springframework.web.filter.ServerHttpObservationFilter.doFilterInternal(ServerHttpObservationFilter.java:113)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:483)
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:116)
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:344)
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:398)
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:903)
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1769)
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)
	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1189)
	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:658)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63)
	at java.base/java.lang.Thread.run(Thread.java:1595)
2025-11-22 19:37:25.238 [http-nio-8080-exec-10] [INFO ] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.buildErrorResponse - No tracker provided; generated automatically: 26fdbe70-6fe5-4075-be3b-b56e4010e900
2025-11-22 19:37:25.239 [http-nio-8080-exec-10] [WARN ] [SLT-003751] c.h.i.handler.GlobalExceptionHandler.logException - [26fdbe70-6fe5-4075-be3b-b56e4010e900] 429 TOO_MANY_REQUESTS - Limit of requests exceeded. Please try again later. | errors=[Limit of 3 requests was exceeded in 10 seconds.]
```
The response is similar to:
```text
{
  "message": "Limit of requests exceeded. Please try again later.",
  "timestamp": "2025-11-22T19:37:25.5434863",
  "code": "429",
  "tracker": "22d67636-f442-40e7-934f-77048627fc3c",
  "errors": [
    "Limit of 3 requests was exceeded in 10 seconds."
  ]
}
```
The tests can be made using the terminal or using a tool like Postman to send multiple requests in a short period of time.
Below the logs and call using terminal:
```shell
for i in {1..10}; do sleep 1; curl -i -H "X-Client-Id: client-1" http://localhost:8080/api/limited; echo; done
```
Result
```text
HTTP/1.1 200 
Content-Type: text/plain;charset=UTF-8
Content-Length: 30
Date: Fri, 07 Nov 2025 22:26:53 GMT

Request Allowed. Limit: 3/10s.
HTTP/1.1 200 
Content-Type: text/plain;charset=UTF-8
Content-Length: 30
Date: Fri, 07 Nov 2025 22:26:54 GMT

Request Allowed. Limit: 3/10s.
HTTP/1.1 200 
Content-Type: text/plain;charset=UTF-8
Content-Length: 30
Date: Fri, 07 Nov 2025 22:26:55 GMT

Request Allowed. Limit: 3/10s.
HTTP/1.1 429 
Content-Type: text/plain;charset=UTF-8
Content-Length: 61
Date: Fri, 07 Nov 2025 22:26:56 GMT

Limite de requisições excedido. Tente novamente mais tarde.
HTTP/1.1 429 
Content-Type: text/plain;charset=UTF-8
Content-Length: 61
Date: Fri, 07 Nov 2025 22:26:57 GMT

Limite de requisições excedido. Tente novamente mais tarde.
HTTP/1.1 429 
Content-Type: text/plain;charset=UTF-8
Content-Length: 61
Date: Fri, 07 Nov 2025 22:26:58 GMT

Limite de requisições excedido. Tente novamente mais tarde.
HTTP/1.1 429 
Content-Type: text/plain;charset=UTF-8
Content-Length: 61
Date: Fri, 07 Nov 2025 22:26:59 GMT

Limite de requisições excedido. Tente novamente mais tarde.
HTTP/1.1 429 
Content-Type: text/plain;charset=UTF-8
Content-Length: 61
Date: Fri, 07 Nov 2025 22:27:00 GMT

Limite de requisições excedido. Tente novamente mais tarde.
HTTP/1.1 429 
Content-Type: text/plain;charset=UTF-8
Content-Length: 61
Date: Fri, 07 Nov 2025 22:27:01 GMT

Limite de requisições excedido. Tente novamente mais tarde.
HTTP/1.1 429 
Content-Type: text/plain;charset=UTF-8
Content-Length: 61
Date: Fri, 07 Nov 2025 22:27:02 GMT

Limite de requisições excedido. Tente novamente mais tarde.

```
In the case you need to use RateLimit in any endpoint, just add the annotation @RateLimit to the method, for example:
```java
@RateLimit
public ResponseEntity<Void> deleteUserById(String userId) {
  System.out.println("Deleting user with ID: " + userId);
  return null;
}
```

### Rate Limit Service Bus Tests

> IMPORTANT: Needs Redis running for rate limit tests.

The rate limit service bus tests are similar to the previous rate limit tests, but in this case the rate limit is applied to service bus message processing.
To test it, send multiple messages to the service bus topic within the rate limit configured (3 messages per 10 seconds).
Below you can see how to implement the rate limit in the service bus listener:
```java@Service
    @RateLimitServiceBus(limit = 3, duration = 10, unit = TimeUnit.SECONDS, keyParameterName = "__MESSAGE__")
    public ResponseEntity<String> processMessage(@RequestBody ProcessMessageSimulation __MESSAGE__) {
        log.info("Processing message for UserID: {}", __MESSAGE__.getUserId());
        return ResponseEntity.ok("Message processed successfully for userId: " + __MESSAGE__.getUserId());
    }

    @RateLimitServiceBus(limit = 5, duration = 10, unit = TimeUnit.SECONDS, keyParameterName = "user")
    public ResponseEntity<String> processMessage2(@RequestBody ProcessMessageSimulation user) {
        log.info("Processing message for User: {}", user.getUserId());
        return ResponseEntity.ok("Message processed successfully for user: " + user.getUserId());
    }
```
Similar results will be obtained as in the previous rate limit tests, but now applied to service bus message processing.
```json
{
  "message": "Limit of requests exceeded. Please try again later.",
  "timestamp": "2025-11-23T00:07:34.7176437",
  "code": "429",
  "tracker": "178f6824-1b2b-4646-bb1e-9e90e871d2e3",
  "errors": [
    "My Limit of 5 requests exceeded for key 'user' in 10 seconds."
  ]
}
```

### MongoDB Tests



### Service Bus Tests

To generate a connection string for your Azure Service Bus queue, follow these steps:

    Navigate to your Service Bus Namespace in the Azure portal.
    In the left menu, expand Settings and select Shared access policies.
    On the Shared access policies page, select the policy named RootManageSharedAccessKey.
    In the Policy: RootManageSharedAccessKey window, click the copy button next to Primary Connection String to copy it to your clipboard. You can paste this value into a text editor for later use.

This connection string will allow you to connect to your Service Bus namespace. For more detailed information, you can refer to the Azure documentation on Service Bus queues.

