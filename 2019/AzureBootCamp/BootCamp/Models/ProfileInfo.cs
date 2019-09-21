using System;

// XML 처리 및 Text 처리 
using System.IO;
using System.Xml;

/* XML Mapping Model Object Template */
/* 해당 XML Mapping */
namespace BootCamp.Models
{
    public class ProfileInfo
    {
        public string Id { get; private set; }

        public string NameKr { get; private set; }
        public string NameEn { get; private set; }

        public string[] Achievements { get; private set; }

        public string Email { get; private set; }
        public string Comment { get; private set; }
        public string FestaLink { get; private set; }
        public string AzureBootCampLink { get; private set; }


        public ProfileInfo(string id, string path)
        {
            this.Id = id;

            if (string.IsNullOrEmpty(path))
            {
                return;
            }

            // XML Parsing
            XmlReader reader = XmlReader.Create(path);      
            XmlDocument doc = new XmlDocument();
            doc.Load(path);
            XmlElement element = doc.DocumentElement;

            if (element == null)
            {
                throw new Exception("메타데이터 파일이 없습니다.");
            }


            this.NameKr = element["Name"]["kr"].InnerText;
            this.NameEn = element["Name"]["en"].InnerText;

            var achievements = element["Achievements"].GetElementsByTagName("Achievement");
            this.Achievements = new string[achievements.Count];
            for (int i = 0; i < this.Achievements.Length; ++i)
            {
                this.Achievements[i] = achievements[i].InnerText;
            }

            this.Email = element["Email"].InnerText;
            this.Comment = element["Comment"].InnerText;
            this.FestaLink = element["FestaLink"].InnerText;
            this.AzureBootCampLink = element["AzureBootCampLink"].InnerText;

        }
    }

  
}
