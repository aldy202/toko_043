/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwsa.toko;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author hp
 */
@Controller
@ResponseBody
public class TokoController {
    
    
    Barang brg = new Barang();
    BarangJpaController brgJPA = new BarangJpaController();
    
    
    @RequestMapping("/get_toko/{id}")
    public String getBrng(@PathVariable("id") int id)
    {
        try{
            brg = brgJPA.findBarang(id);
            String result = brg.getId().toString()+"<br>"+brg.getNama()+"<br>"+brg.getJumlah().toString();
            return result;
            
        }catch(Exception e){return "data tidak ada";}
    }
    
    @RequestMapping("/tokoall")
    public List<Barang> getBarang()
    {
        List<Barang> brg = new ArrayList<Barang>();
        return brgJPA.findBarangEntities();
              
    }
    
    @RequestMapping("/delete_toko/{id}")
    public String delBarang(@PathVariable("id") int id)
            
    {
        try{
            brgJPA.destroy(id);
            return "Data terhapus";
        }catch (Exception e){return "data tidak ditemukan";}
            
        
    }
    @RequestMapping("/post_toko")
    public String postBarang(@RequestBody Barang brg)
    {
       return "hasil";
        
    }
      
}
