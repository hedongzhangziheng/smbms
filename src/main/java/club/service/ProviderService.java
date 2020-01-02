package club.service;

import club.pojo.Provider;

import java.util.List;

public interface ProviderService {
    List<Provider> all();
    List<Provider> allProvider();
    int add(Provider provider);
    Provider findById(Long id);
    int modify(Provider provider);
    int del(Long id);
}
