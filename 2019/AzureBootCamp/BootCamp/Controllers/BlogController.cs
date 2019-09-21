using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using BootCamp.Models;
using Microsoft.AspNetCore.Hosting;


// 블로그 글 CRUD Controller
namespace BootCamp.Controllers
{
    public class BlogController : Controller
    {

        /* 데이터 베이스 연동을 위한 작업 */
        private BlogContext _BlogContext;

        public BlogController(BlogContext blogContext)
        {
            _BlogContext = blogContext;

        }

        // 블로그 리스트 페이지 
        // URL : /Blog/Index
        public IActionResult Index(int page = 0)
        {

             /* 리스트 페이지 Count 조절 */
             int range; // 한 페이지당 쪽 수 
             int startRange; // 페이지 Index 시작 번호
             int blogCount; // 남은 블로그 글 수 ( EX ) 1 ~ 10까지라면 블로그 보여줄 글 갯수 10개 )
             if (page < 0) page = 0;
            range = 10;
           

            // 시작 글, 끝 글 설정 
            startRange = (int)page * range;

            // 기준은 10페이지로 하나 글의 숫자가 10개보다 적을 경우 남은 글 숫자만큼만 출력 
            // EX) 26개인데 20번째 글부터 보여준다면 7개만 보여줌. 
            blogCount = _BlogContext.Blog.Count<Blog>() - startRange;

            // 마지막 페이지 고정
            // 마지막 페이지보다 더 뒤로 가는 Index로 갈 경우 마지막 글이 있는 Index Page로 고정   
            if (blogCount < 0)
            {
                page -= 1; // 마지막 Index로 
                startRange = (int)page * range; // 한 페이지 보여줄 글 수 
                blogCount = _BlogContext.Blog.Count<Blog>() - startRange; // 시작 Index 지정 

            }

            // 기준은 10페이지로 하나 글의 숫자가 10개보다 적을 경우 남은 글 숫자만큼만 출력 
            if (blogCount < range)
            {
                range = blogCount;
            }

            // 해당되는 List 의 글 Count 숫자만큼만 출력
            // 페이징 처리하여 한 페이지의 글 범위 숫자만큼만 보여줌
            List<Blog> blogs = _BlogContext.Blog.Skip(startRange).Take(blogCount).ToList();
            ViewBag.PrePage = page - 1; // 이전 페이지
            ViewBag.NextPage = page + 1; // 시작 페이지
            return View(blogs); // Model로 Blog List를 넘겨줌 
        }

        // 블로그 상세 페이지 
        // URL : /Blog/Detail
        public IActionResult Detail(int BlogId)
        {
            // 해당 Id에 해당하는 Blog글이 미존재 하는 경우 
            if (!ModelState.IsValid)
            {
                return View();
            }
            Blog DetailBlog = _BlogContext.Blog.Find(BlogId);
            return View(DetailBlog);

        }

        // 블로그 작성 페이지 
        // URL : /Blog/Insert
        public IActionResult Insert()
        {
            return View();
        }

        // 블로그 작성
        // URL : /Blog/BlogInsert
        [HttpPost]
        public IActionResult BlogInsert([FromBody] Blog blog)
        {
            if (!ModelState.IsValid)
            {
                return StatusCode(404, "error");
            }
            else
            {
                // 해당되는 Blog Insert
                _BlogContext.Blog.Add(blog);
                _BlogContext.SaveChanges();
                return StatusCode(200, "OK");
            }

        }

        // 블로그 글 삭제
        // URL : /Blog/BlogDelete
        [HttpPost]
        public IActionResult BlogDelete([FromBody] Blog BlogId)
        {
            // 해당되는 BlogId를 찾아 해당 Data를 Remove 
            BlogId = _BlogContext.Blog.Find(BlogId.BlogId);
            _BlogContext.Blog.Remove(BlogId);
            _BlogContext.SaveChanges();
            return StatusCode(200, "OK");
        }

        // 블로그 수정 페이지 
        // URL : /Blog/Update
        public IActionResult Update(int blogId)
        {
            Blog DetailBlog = _BlogContext.Blog.Find(blogId);
            return View(DetailBlog);
        }

        // 블로그 수정
        // URL : /Blog/BlogUpdate
        [HttpPost]
        public IActionResult BlogUpdate([FromBody] Blog blog)
        {
            if (!ModelState.IsValid)
            {
                return StatusCode(404, "error");
            }
            else
            {
                // 해당되는 Blog Insert
                _BlogContext.Blog.Update(blog);
                _BlogContext.SaveChanges();
                return StatusCode(200, "OK");
            }

        }
    }
}

