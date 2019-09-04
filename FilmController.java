package com.zhangsan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangsan.service.FilmService;
import com.zhangsan.bean.Film;
import com.zhangsan.bean.Type;

@Controller
public class FilmController {
	@Resource
	private FilmService service;
	
	@RequestMapping("list.do")
	public String list(Model model,
			@RequestParam(defaultValue="")String fname,
			@RequestParam(defaultValue="1")int cpage) {
		PageHelper.startPage(cpage, 3);
		Map<String, Object> map=new HashMap<>();
		map.put("fname", fname);
		map.put("cpage", cpage);
		List<Map<String,Object>> list = service.findlist(map);
		PageInfo<Map<String, Object>> pageInfo=new PageInfo<>(list);
		model.addAttribute("list", list);
		model.addAttribute("map", map);
		model.addAttribute("page", pageInfo);
		return "list";
	}
	

	@RequestMapping("toadd.do")
	public String toadd(Model model) {
		List<Type> list =service.findtype();
		model.addAttribute("list", list);
		return "add";
	}
	
	@RequestMapping("add.do")
	@ResponseBody
	public Object toadd(String fname,String story,String actor,String time,int[] tids) {
		int i=service.add(fname,story,actor,time);
		int fid=service.findfid();
		int q=service.addmiddle(fid,tids);
		return i;
	}
	
	@RequestMapping("delete.do")
	@ResponseBody
	public Object delete(String fids) {
		int i=0;
		String[] split = fids.split(",");
		for (String string : split) {
			int j=service.delete(Integer.parseInt(string));
			int q=service.deletemiddle(Integer.parseInt(string));
			i+=j;
		}
		return i;
	}
	
	@RequestMapping("toupdate.do")
	public String toupdate(Model model,int fid) {
		Film film=service.getfilm(fid);
		List<Type> list =service.findtype();
		List<Integer> list2=service.gettids(fid);
		model.addAttribute("list", list);
		model.addAttribute("list2", list2);
		model.addAttribute("f", film);
		return "show";
	}
	
	@RequestMapping("update.do")
	@ResponseBody
	public Object update(int fid,String fname,String story,String actor,String time,int[] tids) {
		int i=service.deletemiddle(fid);
		int j=service.update(fid,fname,story,actor,time);
		int q=service.addmiddle(fid,tids);
		return q;
	}
}
