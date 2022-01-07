package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pojo.User;
import com.service.TabService;
import com.service.UserService;
import com.pojo.Group;
import com.pojo.ResultInfo;
import com.pojo.Tables;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.PrintWriter;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	public UserService userService;
	@Autowired
	public TabService tabService;
	
	//用户登录
	@RequestMapping(value = "/userlogin", method = RequestMethod.POST)
	public ModelAndView login(String username,String password,HttpSession session) {
		 ModelAndView mav = new ModelAndView();
		 User user = userService.userLogin(username, password);
		 if(user==null) {
			 mav.addObject("msg", "用户不存在");
			 mav.setViewName("login");
		 }
		 else {
			 session.setAttribute("user", user);
			 mav.addObject("msg", "登录成功");
			 mav.setViewName("index");
		 }
		 return mav;
	}
	
	//用户注册
	@RequestMapping(value = "/userreg", method = RequestMethod.POST)
	public ModelAndView register(String username,String password) {
		 ModelAndView mav = new ModelAndView();
		 User user = userService.checkUser(username);
		 if(user==null) {
			 User newuser=new User();
			 newuser.setUsername(username);
			 newuser.setPassword(password);
			 newuser.setRole("user");
			 newuser.setGroupid(0);
			 userService.insert(newuser);
			 mav.addObject("msg", "注册成功，请登录");
			 mav.setViewName("login");
		 }else {
			 mav.addObject("msg", "用户已经存在");
			 mav.setViewName("register");
		 }
		 return mav;
	}
	
	//获取所有用户
	@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	public ModelAndView userlist(HttpServletRequest request) {
		String username=request.getParameter("username");
		String usergroup=request.getParameter("usergroup");
		ModelAndView mav = new ModelAndView();
		List<User> list = userService.list();
		List<User> nlist = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			User u = list.get(i);
			if(username==null&&usergroup==null) {
				if(u.getGroupid()==0) {
					u.setGroupname("");
				}else {
					//获取用户组
					Group group=userService.getGroup(u.getGroupid());
					u.setGroupname(group.getGroupname());
				}
				nlist.add(u);
			}else {
				if(!username.equals("")) {
					if(u.getUsername().equals(username)) {
						if(usergroup.equals("")) {
							if(u.getGroupid()==0) {
								u.setGroupname("");
							}else {
								//获取用户组
								Group group=userService.getGroup(u.getGroupid());
								u.setGroupname(group.getGroupname());
							}
							nlist.add(u);
						}else {
							if(u.getGroupname().equals(usergroup)) {
								if(u.getGroupid()==0) {
									u.setGroupname("");
								}else {
									//获取用户组
									Group group=userService.getGroup(u.getGroupid());
									u.setGroupname(group.getGroupname());
								}
								nlist.add(u);
							}
						}
					}
				}else {
					if(usergroup.equals("")) {
						if(u.getGroupid()==0) {
							u.setGroupname("");
						}else {
							//获取用户组
							Group group=userService.getGroup(u.getGroupid());
							u.setGroupname(group.getGroupname());
						}
						nlist.add(u);
					}else {
						if(u.getGroupid()==0) {
							u.setGroupname("");
						}else {
							//获取用户组
							Group group=userService.getGroup(u.getGroupid());
							u.setGroupname(group.getGroupname());
						}
						if(u.getGroupname().equals(usergroup)) {
							nlist.add(u);
						}
					}
				}
			}
		}
		//返回数据
	    mav.addObject("userlist", nlist);
		//页面跳转
		mav.setViewName("listUser");
		return mav;
	}
	
	//设置用户组
	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public ModelAndView editUser(int id) {
		User user = userService.getUser(id);
		List<Group> list = userService.glist();
		ModelAndView mav = new ModelAndView();
		mav.addObject("grouplist",list);
		mav.addObject("user",user);
		mav.setViewName("editUser");
		return mav;
	}
	
	//更新用户
	@RequestMapping(value = "/updateUser",method = RequestMethod.POST)
	public ModelAndView updateUser(int id,int groupid) {
		User user = new User();
		user.setId(id);
		user.setGroupid(groupid);
		userService.update(user);
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "设置成功");
		mav.setViewName("listUser");
		return mav;
	}
	
	//添加用户组
	@RequestMapping(value = "/addGroup",method = RequestMethod.POST)
	public ModelAndView addGroup(String groupname) {
		//检测用户组是否重复
		int flag = userService.findGroup(groupname);
		ModelAndView mav = new ModelAndView();
		if(flag==0) {
			Group group = new Group();
			group.setGroupname(groupname);
			userService.insertGroup(group);
			mav.addObject("msg", "添加成功");
		} else {
			mav.addObject("msg", "添加失败");
		}
		mav.setViewName("addGroup");
		return mav;
	}
	
	//获取所有用户组
	@RequestMapping(value = "/grouplist", method = RequestMethod.GET)
	public ModelAndView grouplist() {
		ModelAndView mav = new ModelAndView();
		List<Group> list = userService.glist();
	    mav.addObject("grouplist", list);
		mav.setViewName("listGroup");
		return mav;
	}
	
	//删除用户组
	@RequestMapping(value = "/delGroup", method = RequestMethod.GET)
	public ModelAndView delGroup(int id) {
		List<User> list = userService.getUserByGroup(id);
		ModelAndView mav = new ModelAndView();
		if(list.size()!=0) {
			mav.addObject("msg", "该用户组有用户，不能删除");
		}else {
			userService.delGroup(id);
			mav.addObject("msg", "删除成功");
		}
		mav.setViewName("listGroup");
		return mav;
	}
	
	//填报信息
	@RequestMapping(value = "/inputinfo", method = RequestMethod.GET)
	public ModelAndView inputinfo() {
		//获取所有表格
		List<Tables> list=tabService.getgroups();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("tablist",list);
		mav.setViewName("inputInfo");
		return mav;
	}
	
	@RequestMapping(value = "/getTabByGroup", method = RequestMethod.POST)
	public void getTabByGroup(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String selgroup=request.getParameter("selgroup");
		List<Tables> alllist=tabService.getlist();
		List<Tables> nlist=new ArrayList<Tables>();
		for(Tables tab:alllist) {
			if(tab.getTabgroup().equals(selgroup)) {
				nlist.add(tab);
			}
		}
		ResultInfo info=new ResultInfo();
		info.setCode(200);
		info.setData(JSONObject.toJSON(nlist));
		info.setMessage("获取成功");
		PrintWriter writer = null;
		try {
            writer = response.getWriter();
            writer.write(JSONObject.toJSONString(info));
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }		
	}
	
	//填报信息下一步
	@RequestMapping(value = "/nextinputinfo",method = RequestMethod.POST)
	public ModelAndView nextinputinfo(int tabid,HttpServletRequest request) {
		Tables tab=tabService.getTab(tabid);
		String cols[] = tab.getCncol().split(",");
		ModelAndView mav = new ModelAndView();
		mav.addObject("tab",tab);
		mav.addObject("cols", cols);
		if(tab.getCncol().contains("附件")) {
			mav.addObject("isupload","y");
		}else {
			mav.addObject("isupload","n");
		}
		ArrayList<String[]> opArr=new ArrayList<String[]>();
		for(int i=0;i<cols.length;i++) {
			if(cols[i].contains("select")) {
				String [] arr=cols[i].split(":");
				String option=arr[1].substring(0,arr[1].length()-1);
				opArr.add(option.split("-"));
			}
		}
		request.setAttribute("options", opArr);
		mav.setViewName("inputInfoNext");
		return mav;
	}
	
	//提交填报信息
	@RequestMapping(value = "/addinputinfo",method = RequestMethod.POST)
	public String addinputinfo(String tabid,@RequestParam MultipartFile uploadfile,HttpServletRequest request) {
		//获取登录用户
		User loginuser = (User)request.getSession().getAttribute("user");
		String newfile="";
		// 存储路径
		String savePath = request.getServletContext().getRealPath("/static/img");
		try {
			String originalFilename=uploadfile.getOriginalFilename();
			if(!originalFilename.equals("")) {
				newfile = originalFilename;
				File targetFile = new File(savePath,newfile);
				uploadfile.transferTo(targetFile);
			}
		}catch(Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("resultMSG", "文件格式不对或者超过大小");
			return "redirect:/user/inputinfo";
		}
		Tables tab = tabService.getTab(Integer.parseInt(tabid));
		String cols[]=tab.getCncol().split(",");
		String colnames="uid,";
		String colvalues =loginuser.getId()+",";
		for(int i=1;i<cols.length;i++) {
			if(cols[i].equals("附件")) {
				colvalues=colvalues+"'<a href=\"http://121.89.235.113/info_sysytem//static/img/"+newfile+"\" target=\"_blank\">查看</a>',";
				colnames=colnames+"col"+i+",";
			}else {
				String temp="col"+i;
				colnames=colnames+temp+",";
				String colValue = request.getParameter(temp);
				colvalues=colvalues+"'"+colValue+"',";
			}
		}
		if(colnames.endsWith(",")) {
			colnames=colnames.substring(0,colnames.length()-1);
		}
		if(colvalues.endsWith(",")) {
			colvalues=colvalues.substring(0,colvalues.length()-1);
		}
		String sql="insert into "+tab.getEnname()+" ("+colnames+") values ("+colvalues+")";
		int row=tabService.insertData(sql);
		if(row>0) {
			request.getSession().setAttribute("resultMSG", "填报成功");
		}else {
			request.getSession().setAttribute("resultMSG", "填报失败");
		}
		return "redirect:/user/inputinfo";
	}
	
	@RequestMapping(value = "/userTablist",method = RequestMethod.GET)
	public ModelAndView userTablist() {
		//获取所有表格
		List<Tables> list=tabService.getlist();
		ModelAndView mav = new ModelAndView();
		mav.addObject("tablist",list);
		mav.setViewName("seeInfos");
		return mav;
	}
	
	//获取用户所有填报信息
	@RequestMapping(value = "/queryTablist",method = RequestMethod.POST)
	public ModelAndView queryTablist(int tabid,HttpSession session,HttpServletRequest request) {
		Tables tab = tabService.getTab(tabid);
		//获取登录用户
		User loginuser = (User)request.getSession().getAttribute("user");
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("tableName", tab.getEnname());
		params.put("uid",loginuser.getId());
		List<Map<String,Object>> DataSet = tabService.GetTableData(params);
		request.setAttribute("tab", tab);
		request.setAttribute("data", DataSet);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("userTabInfo");
		return mav;
	}
	
	//退出后台系统
	 @RequestMapping("/outLogin")
	 public ModelAndView outLogin(HttpSession session){
		 ModelAndView mav = new ModelAndView();
		 session.invalidate();
		 mav.setViewName("login");
		 return mav;
	 }
	 
	 //删除填报信息
	 @RequestMapping(value = "/delInfo",method = RequestMethod.GET)
	 public String delInfo(int tabid,int id) {
		Tables tab=tabService.getTab(tabid);
		String sql="delete from "+tab.getEnname()+" where id="+id;
		int row=tabService.deleteData(sql);
		return "redirect:/user/userTablist";
	 }
}
