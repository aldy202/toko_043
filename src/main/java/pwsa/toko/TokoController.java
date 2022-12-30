/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwsa.toko;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hp
 */
@RestController
@ResponseBody
public class TokoController {
    
    //membuat object baru untuk memanggil class barang
    Barang brg = new Barang();
    //membuat object baru untuk memanggil class barangJPAcontroller
    BarangJpaController brgJPA = new BarangJpaController();
    
    
    @GetMapping("/toko/{id}")//menggunakan request mapping berdasarkan id
    public String getBrng(@PathVariable("id") int id)//membuat method untuk menampilkan data berdasarkan id
    {
        try{
            brg = brgJPA.findBarang(id);//mencari id
            String result = brg.getId().toString()+"<br>"+brg.getNama()+"<br>"+brg.getJumlah().toString();//menampilkan data
            return result;//mengembalikan nilai result
            
        }catch(Exception e){return "data tidak ada";}//menampilkan pesan jika data tidak ada
    }
    
    @GetMapping("/toko")
    public List<Barang> getBarang()//membuat method untuk menampilkan semua data
    {
        List<Barang> brg = new ArrayList<Barang>();//menyimpan data kedalam array list
        return brgJPA.findBarangEntities();//return semua nilai entity yg ada di barang
              
    }
    
    @DeleteMapping("/toko/{id}")
    public String delBarang(@PathVariable("id") int id)//membuat method untuk        
    {
        try{
            brgJPA.destroy(id);
            return "Data terhapus";
        }catch (Exception e){return "data tidak ditemukan";}
            
        
    }
    @PostMapping("/toko")
    public String postBarang(@RequestBody Barang brg)
    {
       try{
           brgJPA.create(brg);
           return "berhasil";
       }catch(Exception e){return "data tidak berhasil di input";}
        
    }
    @PutMapping("/toko/{id}")
    public String putBarang(@RequestBody Barang barag, @PathVariable("id") int id)
    {
        try
        {
            brg = brgJPA.findBarang(id);
            barag.setId(id);
            brgJPA.edit(barag);
            return "berhasil";

        }catch(Exception e){return "data tidak berhasil di edit";}
        
    }
      
}
