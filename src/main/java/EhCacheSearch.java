import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class EhCacheSearch {
  @Configuration
  public static class ApplicationConfiguration {

    /* @Bean
    public SpringEmbeddedCacheManagerFactoryBean springCache() {
      return new SpringEmbeddedCacheManagerFactoryBean();
    }*/

    @Bean
    public CachePlayground playground() {
      return new CachePlayground();
    }
  }

  public static class CachePlayground {

    @Autowired private CacheManager cacheManager;

    public void add(String key, String value) {
      cacheManager.getCache("default").put(key, value);
    }

    public String getContent(String key) {
      return cacheManager.getCache("default").get(key).get().toString();
    }
  }

  public static void main(String[] args) {
    /*ApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
    // Create a cache programmatically for the example. There is no default cache
    EmbeddedCacheManager cacheManager =
        applicationContext.getBean(SpringEmbeddedCacheManager.class).getNativeCacheManager();
    cacheManager
        .administration()
        .withFlags(CacheContainerAdmin.AdminFlag.VOLATILE)
        .getOrCreateCache("default", new ConfigurationBuilder().build());

    CachePlayground cachePlayground = applicationContext.getBean(CachePlayground.class);

    cachePlayground.add("Infinispan", "Is cool!");
    System.out.println(cachePlayground.getContent("Infinispan"));*/
  }
}
