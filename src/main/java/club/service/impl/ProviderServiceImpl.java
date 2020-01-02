package club.service.impl;

import club.dao.ProviderDao;
import club.pojo.Provider;
import club.service.ProviderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ProviderServiceImpl implements ProviderService {
    @Resource
    private ProviderDao providerDao;
    @Override
    public List<Provider> all() {
        return providerDao.all();
    }

    @Override
    public List<Provider> allProvider() {
        return providerDao.selectList(null);
    }

    @Override
    public int add(Provider provider) {
        return providerDao.insert(provider);
    }

    @Override
    public Provider findById(Long id) {
        return providerDao.selectById(id);
    }

    @Override
    public int modify(Provider provider) {
        return providerDao.updateById(provider);
    }

    @Override
    public int del(Long id) {
        return providerDao.deleteById(id);
    }
}
