package club.controller;

import club.pojo.Bill;
import club.pojo.Provider;
import club.service.BillService;
import club.service.ProviderService;
import club.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/sys")
public class ProviderController {

    @Resource
    private BillService billService;

    @Resource
    private ProviderService providerService;

    @RequestMapping("/provider")
    public String bill(HttpSession session, String queryProCode, String queryProName, Integer pageIndex){
        List<Provider> providerList = providerService.allProvider();
        session.setAttribute("queryProCode", queryProCode);
        session.setAttribute("queryProName", queryProName);
        session.setAttribute("proList", providerList);
        return "pro/providerlist";
    }

    @RequestMapping("/provideradd")
    public String provideradd(){
        return "pro/provideradd";
    }

    @RequestMapping("/provideraddsave")
    public String doprovideradd(Provider provider){
        providerService.add(provider);
        return "redirect:/sys/provider";
    }

    @RequestMapping("/proview/{id}")
    public String billview(HttpSession session ,@PathVariable Long id){
        Provider provider = providerService.findById(id);
        session.setAttribute("provider", provider);
        return "pro/providerview";
    }

    @RequestMapping("/providermodify")
    public String modify(HttpSession session ,Long proid){
        Provider provider = providerService.findById(proid);
        session.setAttribute("provider", provider);
        return "pro/providermodify";
    }

    @RequestMapping("/providermodifysave")
    public String doprovidermodify(Provider provider){
        providerService.modify(provider);
        return "redirect:/sys/provider";
    }

    @RequestMapping("/delprovider")
    @ResponseBody
    public Result providerdel(Long proid){
        Result result = new Result();
        Provider provider = providerService.findById(proid);
        long count = billService.selectCountByProid(proid);
        if(count != 0){
            result.setDelResult(Long.toString(count));
        }else {
            int del = providerService.del(proid);
            if(del == 0){
                result.setDelResult("false");
            }else{
                result.setDelResult("true");
            }
        }
        return result;
    }
}
