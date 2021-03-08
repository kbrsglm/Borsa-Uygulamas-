package com.example.ProductManagement.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.example.ProductManagement.implementation.Productimplementation;
import com.example.ProductManagement.implementation.UserImplementation;
import com.example.ProductManagement.model.AlimSatim;
import com.example.ProductManagement.model.Products;
import com.example.ProductManagement.model.User;

@Controller
public class ProductController {

	public static ArrayList<User>  userlistesi=new ArrayList<User>();
	
	public static ArrayList<AlimSatim> alimsatimlistesi =new ArrayList<AlimSatim>();
	
	@Autowired
	private Productimplementation productImplementation;
	
	
	@Autowired
	private UserImplementation userImplementation;

	
	@GetMapping("/home")
	public String getHome(Model model) {
		List<Products> listProducts=productImplementation.getProducts();
		System.out.println(listProducts.get(0));
		model.addAttribute("listProducts", listProducts);
		return "products";
	}
	
	@RequestMapping("/new")
	public String showNewProductForm(Model model) {
		Products p=new Products();
		model.addAttribute("product", p);
		return "new_product";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveNewProduct(@ModelAttribute("product") Products product) {
		productImplementation.saveProducts(product);
		
		return "redirect:/home";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView EditProduct(@PathVariable(name="id") int id) {
		ModelAndView mav=new ModelAndView("edit_product");
		Products product= productImplementation.getProduct(id);
		mav.addObject("product",product);
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String DeleteProduct(@PathVariable(name="id") int id) {
		productImplementation.deleteProductById(id);
		return "redirect:/home";
	}
	
	@GetMapping("/login")
	public ModelAndView Login() {
		ModelAndView mav=new ModelAndView("login");
		mav.addObject("user",new Products());
		return mav;
	}
	
	@PostMapping("/login")
	public String Login(@ModelAttribute("user") Products user) {
		Products auhuser=productImplementation.login(user.getUsername(), user.getPassword());
		System.err.println(auhuser+user.getUsername()+user.getPassword());
		if(Objects.nonNull(auhuser)) {
			return "redirect:/hisselisteleme";
		}else {
			return "redirect:/login";
		}
		//return null;
		
	}
	@GetMapping("/hisselisteleme")
	public String getHisseListeleme(Model model) {
		List<User> listUsers=userImplementation.getUsers();
		System.out.println(listUsers.get(0));
		model.addAttribute("listUser",ProductController.userlistesi);
	
		return "hisselisteleme";
	}
	@RequestMapping("/hisseekle")
	public String showNewHisseForm(Model model) {
		User u=new User();
		model.addAttribute("user", u);
		return "hisse_ekleme";
	}
	
	@RequestMapping(value="/save1",method=RequestMethod.POST)
	public String saveNewHisse(@ModelAttribute("user") User user) {
		userImplementation.saveUser(user);
		user.setTarih(new Date());
		user.setUcret(randomSkaicius());
		ProductController.userlistesi.add(user);
		
		
		return "redirect:/hisselisteleme";
	}
	
	
	@RequestMapping("/edit1/{id}")
	public ModelAndView EditUser(@PathVariable(name="id") int id) {
		ModelAndView mav=new ModelAndView("edit_user");
		User user= userImplementation.getUser(id);
		mav.addObject("user",user);
		return mav;
	}
	@RequestMapping("/delete1/{id}")
	public String DeleteUser(@PathVariable(name="id") int id) {
		userImplementation.deleteUserById(id);
		return "redirect:/hisselisteleme";
	}
	
	public int randomSkaicius() {
        Random rand = new Random();
        int skaicius = (int) (Math.random() * 1000 + 1);
        return skaicius;
    }
	@GetMapping("/hissealimlisteleme")
	public String getHissealimListeleme(Model model) {
		List<User> listUsers=userImplementation.getUsers();
		System.out.println(listUsers.get(0));
		model.addAttribute("tests", listUsers);
		model.addAttribute("alimsatimx",ProductController.alimsatimlistesi);
		return "hissealimlisteleme";
	}
	


	@RequestMapping(value = { "/hissealimsatim" }, method = RequestMethod.GET)
	public String selectOptionExample1Page(Model model ) {
		User u=new User();
		model.addAttribute("user", u);
		
	    List<User> tests = userImplementation.getUsers();
	    model.addAttribute("tests", tests);
		return "hisse_alim_satim";
	   
	}
	
	
	@RequestMapping(value="/save2",method=RequestMethod.POST)
	public String saveNewUser(@ModelAttribute("alimsatim") AlimSatim alimSatim,@RequestParam(value="action", required=true) String action) {
		
		 if (action.equals("ekle")) {
		        // do something here 
			 	for (int i=0;i<ProductController.userlistesi.size();i++) {
					if(alimSatim.getTestOrder().equals(ProductController.userlistesi.get(i).getKod())) {
						alimSatim.toplam=Integer.parseInt(alimSatim.getMiktar())*ProductController.userlistesi.get(i).getUcret();
					}
				}
			 	
				ProductController.alimsatimlistesi.add(alimSatim);
				
		    }

		    if (action.equals("sil")) {
		    	for (int i=0;i<ProductController.userlistesi.size();i++) {
					if(alimSatim.getTestOrder().equals(ProductController.userlistesi.get(i).getKod())) {
						   for (int j=0;j< ProductController.alimsatimlistesi.size();j++) {
							if(alimSatim.getTestOrder().equals(ProductController.alimsatimlistesi.get(j).getTestOrder())){
								ProductController.alimsatimlistesi.get(j).toplam=ProductController.alimsatimlistesi.get(j).toplam-Integer.parseInt(alimSatim.getMiktar())*ProductController.userlistesi.get(i).getUcret();
								Integer miktar=Integer.parseInt(ProductController.alimsatimlistesi.get(j).getMiktar())-Integer.parseInt(alimSatim.getMiktar());
								ProductController.alimsatimlistesi.get(j).setMiktar(miktar.toString());
							}
					}
					}
				}
				
		    }
		    
		

		return "redirect:/hissealimlisteleme";
	}
	
	
	

	
}
