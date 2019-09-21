using PortfolioWeb.Classes;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Web;
using System.Xml;
using System.Xml.Linq;

namespace PortfolioWeb
{
    public class ProfileInfo
    {
        public string Id { get; private set; }

        public string NameKr { get; private set; }
        public string NameEn { get; private set; }
        public string Job { get; private set; }

        public University UniversityInfo { get; private set; }

        public string[] Achievements { get; private set; }

        public Activity[] Activities { get; private set; }



        public string Email { get; private set; }
        public string Comment { get; private set; }

        public ProjectInfo[] Projects { get; private set; }



        // Github, Linkedin  링크 추가
        public string GithubLink { get; private set; }
        public string LinkedinLink { get; private set; }


        // 경력, 수상경력 추가 
        public string[] Jobs { get; private set; }
        public string[] Awards { get; private set; }

        public ProfileInfo(string id, string path)
        {
            this.Id = id;

            if (string.IsNullOrEmpty(path))
            {
                return;
            }

            XmlReader reader = XmlReader.Create(path);

            XmlDocument doc = new XmlDocument();
            doc.Load(path);

            XmlElement element = doc.DocumentElement;

            if (element == null)
            {
                throw new Exception("메타데이터 파일이 없습니다.");
            }

            // Xml 파싱

            this.NameKr = element["Name"]["kr"].InnerText;
            this.NameEn = element["Name"]["en"].InnerText;

            this.Job = element["Job"].InnerText;

            var univ = element["University"];
            this.UniversityInfo = new University(
                univ["Name"].InnerText,
                univ["Major"].InnerText,
                univ["GraduationDate"].InnerText
                );

            var achievements = element["Achievements"].GetElementsByTagName("Achievement");
            this.Achievements = new string[achievements.Count];

            for (int i = 0; i < this.Achievements.Length; ++i)
            {
                this.Achievements[i] = achievements[i].InnerText;
            }

            var activities = element["Activities"].GetElementsByTagName("Activity");
            this.Activities = new Activity[activities.Count];

            for (int i = 0; i < this.Activities.Length; ++i)
            {
                this.Activities[i] = new Activity(
                    activities[i]["Name"].InnerText,
                    activities[i]["Id"].InnerText,
                    activities[i]["Url"].InnerText
                    );
            }

            this.Email = element["Email"].InnerText;

            this.Comment = element["Comment"].InnerText;

            var projects = element.GetElementsByTagName("Project");
            this.Projects = new ProjectInfo[projects.Count];

            for (int i = 0; i < this.Projects.Length; ++i)
            {
                this.Projects[i] = new ProjectInfo(
                    projects[i]["DirName"].InnerText,
                    projects[i]["Name"].InnerText,
                    projects[i]["Topic"].InnerText,
                    projects[i]["Comment"].InnerText,
                    path
                    );
            }

            //  깃허브, 링크드인, 직업, 수상 경력 추가 
            this.GithubLink   = element["GithubLink"].InnerText;
            this.LinkedinLink = element["LinkedinLink"].InnerText;


            var jobactivities = element["Jobs"].GetElementsByTagName("JobsActivity");
            this.Jobs = new string[jobactivities.Count];

            for (int i = 0; i < this.Jobs.Length; ++i)
            {
                this.Jobs[i] = jobactivities[i].InnerText;
            }


            var awardactivities = element["Awards"].GetElementsByTagName("AwardsActivity");
            this.Awards = new string[awardactivities.Count];

            for (int i = 0; i < this.Awards.Length; ++i)
            {
                this.Awards[i] = awardactivities[i].InnerText;
            }


            //root = root.Element("Information");

            //var names = root.Elements("Name");
            //this.NameKr = names.ElementAt(0).Value;
            //this.NameEn = names.ElementAt(1).Value;

            //this.Job = root.Element("Job").Value;
        }
    }

    public class ProjectInfo
    {
        public string DirName { get; private set; }
        public string Name { get; private set; }
        public string Topic { get; private set; }
        public string Comment { get; private set; }
        public string Thumbnail { get; private set; }

        public ProjectInfo(string dirName, string name, string topic, string comment, string metadataPath)
        {
            this.DirName = dirName;
            this.Name = name;
            this.Topic = topic;
            this.Comment = comment;

            FileInfo fInfo = new FileInfo(metadataPath);
            // ~/App_Data/User
            DirectoryInfo dInfo = fInfo.Directory;
            
            // ~/App_Data/User/ProjectDir/0.jpg
            var coverImagePath = Path.Combine(dInfo.FullName, dirName, "tn.jpg");

            try
            {
                // 표지 이미지를 base64 문자열 형태로 변환
                this.Thumbnail = ImageUtil.ToBase64String(coverImagePath);
            }
            catch(Exception)
            {
                this.Thumbnail = string.Empty;
            }

        }
    }

   


    public class Activity
    {
        public string Name { get; private set; }
        public string Id { get; private set; }
        public string Url { get; private set; }

        public Activity(string name, string id, string url)
        {
            this.Name = name;
            this.Id = id;
            this.Url = url;
        }
    }

    public class University
    {
        public string Name { get; private set; }
        public string Major { get; private set; }
        public string GraduationDate { get; private set; }

        public University(string name, string major, string gradDate)
        {
            this.Name = name;
            this.Major = major;
            this.GraduationDate = gradDate;
        }
    }

    // 직장,수상 경력 추가 
    public class Jobs
    {
        public string JobsActivity { get; private set; }

        public Jobs(string JobsActivity)
        {
            this.JobsActivity = JobsActivity;
        }
    }

    public class Awards
    {
        public string AwardsActivity { get; private set; }

        public Awards(string name, string id, string url)
        {
            this.AwardsActivity = AwardsActivity;

        }
    }

}