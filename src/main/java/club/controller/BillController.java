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
public class BillController {
    @Resource
    private BillService billService;

    @Resource
    private ProviderService providerService;

    @RequestMapping("/bill")
    public String bill(HttpSession session, String productName, Integer providerId, Integer isPayment, Integer pageIndex){
        int pageSize = 3;
        List<Provider> providerList = providerService.all();
        int count = billService.count(productName, providerId, isPayment);
        session.setAttribute("totalCount", count);
        int totalPageCount;
        if(count % pageSize == 0){
            totalPageCount = count/pageSize;
        }else {
            totalPageCount = count/pageSize + 1;
        }
        if(pageIndex == null) pageIndex = 1;
        List<Bill> bList = billService.showByPage(productName, providerId, isPayment, pageIndex, pageSize);
        session.setAttribute("productName", productName);
        session.setAttribute("providerId", providerId);
        session.setAttribute("isPayment", isPayment);
        session.setAttribute("plist", providerList);
        session.setAttribute("totalPageCount", totalPageCount);
        session.setAttribute("currentPageNo", pageIndex);
        session.setAttribute("blist", bList);
        return "bill/billlist";
    }

    @RequestMapping("/billadd")
    public String billadd(){
        return "bill/billadd";
    }

    @RequestMapping("/dobilladd")
    public String dobilladd(Bill bill){
        billService.add(bill);
        return "redirect:/sys/bill";
    }

    @RequestMapping("/billselect")
    @ResponseBody
    public Object allProvider(){
        return providerService.all();
    }

    @RequestMapping("/billview/{id}")
    public String billview(HttpSession session ,@PathVariable Long id){
        Bill bill = billService.findById(id);
        session.setAttribute("bill", bill);
        return "bill/billview";
    }

    @RequestMapping("/billmodify")
    public String modify(HttpSession session ,Long billid){
        Bill bill = billService.findById(billid);
        session.setAttribute("bill", bill);
        return "bill/billmodify";
    }

    @RequestMapping("/dobillmodify")
    public String dobillmodify(Bill bill){
        billService.modify(bill);
        return "redirect:/sys/bill";
    }

    @RequestMapping("/billdel")
    @ResponseBody
    public Object billdel(Long billid){
        Result result = new Result();
        Bill bill = billService.findById(billid);
        if(bill == null){
            result.setDelResult("notexist");
        }else {
            int del = billService.del(billid);
            if(del == 0){
                result.setDelResult("false");
            }else{
                result.setDelResult("true");
            }
        }
        return result;
    }
}
