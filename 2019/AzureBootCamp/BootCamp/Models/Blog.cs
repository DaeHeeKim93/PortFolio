using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

// Blog Table 
namespace BootCamp.Models
{
    public class Blog
    {
        [Key] // Primary Key 설정
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int BlogId { get; set; } // Type = "int" // ID 형식은 ***ID
        public string Title { get; set; } // Type = "varchar"
        public string Context { get; set; } // Type = "LONGTEXT"
        public DateTime Regdate { get; set; } // Type = "DATETIME
        public string ThumbNail { get; set; } // Type = "VARCHAR"

    }
}