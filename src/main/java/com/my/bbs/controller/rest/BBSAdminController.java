package com.my.bbs.controller.rest;


import com.my.bbs.entity.Admin;
import com.my.bbs.entity.BBSPost;
import com.my.bbs.entity.BBSTitle;
import com.my.bbs.entity.BBSUser;
import com.my.bbs.service.BBSPostCommentService;
import com.my.bbs.service.BBSPostService;
import com.my.bbs.service.BBSUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BBSAdminController {
    @Autowired
    BBSUserService bbsUserService;

    @Autowired
    BBSPostService bbsPostService;

    @Autowired
    BBSPostCommentService commentService;



    @RequestMapping({"/toAdmin","/","admin","tologin"})
    public String toAdmin(){
        return "/admin/login";
    }

    @RequestMapping("/toindex")
    public String toindex(Model model){
        List<BBSUser> allUser = bbsUserService.getAllUser();
        List<BBSPost> allPost = bbsPostService.getAllPost();
        List<BBSPost> allPost2=new ArrayList<>();
        for (BBSPost bbsPost : allPost) {
            Byte postStatus = bbsPost.getPostStatus();
            if (postStatus==0){
                allPost2.add(bbsPost);
            }
        }
        List<BBSTitle> allTitle = bbsPostService.getAllTitle();
        int numComment = commentService.getNumComment();
        model.addAttribute("UserNum",allUser.size());
        model.addAttribute("PostNum",allPost.size());
        model.addAttribute("CTNum",numComment);
        model.addAttribute("allPost",allPost2);
        model.addAttribute("allTitle",allTitle);
        return "/admin/index_v1";
    }

    @RequestMapping("/login")
    public String login(Admin admin, Model model){
        String aname = admin.getAname();
        String apwd = admin.getApwd();
        if (aname!=null&&!aname.equals("")){
            Admin admin1 = bbsUserService.getAdmin(aname);
            if (admin1!=null){
                if (admin1.getApwd().equals(apwd)){
                    return "redirect:/admin/toindex";
                }
                model.addAttribute("msg","密码错误");
                return "/admin/login";
            }
        }
        model.addAttribute("msg","账号有误");
        return "/admin/login";
    }
    @RequestMapping("/toUserAdmin/{firstUser}")
    public String toUserAdminWithData(Model model,@PathVariable("firstUser") Long firstUser){
        List<BBSUser> allUser = bbsUserService.getAllUser();
        if (firstUser==null){
            model.addAttribute("checkuser",allUser.get(0));
        }else {
            BBSUser userById = bbsUserService.getUserById(firstUser);
            model.addAttribute("checkuser",userById);
        }
        model.addAttribute("allUser",allUser);
        return "/admin/clients";
    }

    @RequestMapping("/toUserSearch")
    public String toUserAdmin(@RequestParam("value")String value, Model model){
        List<BBSUser> allUser = bbsUserService.getUserBySearch(value);
        model.addAttribute("checkuser",allUser.get(0));
        model.addAttribute("allUser",allUser);
        return "/admin/clients";
    }


    @RequestMapping("/toUserAdmin")
    public String toUserAdmin(Model model){
        List<BBSUser> allUser = bbsUserService.getAllUser();
            model.addAttribute("checkuser",allUser.get(0));
        model.addAttribute("allUser",allUser);
        return "/admin/clients";
    }

    @RequestMapping("/toBlogs")
    public String  toBlogs(Model model){
        List<BBSPost> allPost = bbsPostService.getAllPost();
        for (BBSPost bbsPost : allPost) {
            System.out.println(bbsPost.toString());
        }
        model.addAttribute("allPost",allPost);
        return "/admin/blog";
    }

    @RequestMapping("/changPostOne/{pid}")
    public String changPostOne(@PathVariable("pid")Long pid){
        bbsPostService.changePostState( 1,pid);
        return "redirect:/admin/toBlogs";
    }
    @RequestMapping("/changPostTwo/{pid}")
    public String changPostTwo(@PathVariable("pid")Long pid){
        bbsPostService.changePostState( 2,pid);
        return "redirect:/admin/toBlogs";
    }
    @RequestMapping("/toBlogsSet")
    public String toBlogsSet(Model model){
        List<BBSTitle> allTitle = bbsPostService.getAllTitle();
        model.addAttribute("allTitle",allTitle);
        return "/admin/blogset";
    }
    @RequestMapping("/addTitle")
    public String addTitle(BBSTitle bbsTitle){
        bbsPostService.addtitle(bbsTitle);
        return "redirect:/admin/toBlogsSet";
    }
    @RequestMapping("/delTile/{tid}")
    public String delTile(@PathVariable("tid")Integer tid){
        bbsPostService.deltitle(tid);
        return "redirect:/admin/toBlogsSet";
    }
    @RequestMapping("/changeUserState/{uid}")
    public String changeUserState(@PathVariable("uid")Long uid){
        bbsUserService.changeUserState(uid);
        return "redirect:/admin/toUserAdmin";
    }
    @RequestMapping("/deluser/{uid}")
    public String deluser(@PathVariable("uid")Long uid){
        bbsUserService.delUser(uid);
        return "redirect:/admin/toUserAdmin";
    }






}
