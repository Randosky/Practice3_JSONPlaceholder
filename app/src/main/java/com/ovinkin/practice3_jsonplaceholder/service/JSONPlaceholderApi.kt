package com.ovinkin.practice3_jsonplaceholder.service

import model.Comment
import model.Post
import model.user.Company
import model.user.User
import model.user.address.Address
import model.user.address.Geo

class JSONPlaceholderApi {

    private val posts: List<Post> = listOf(
        Post(
            userId = 1,
            id = 1,
            title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            body = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
        ),
        Post(
            userId = 1,
            id = 2,
            title = "qui est esse",
            body = "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"
        ),
        Post(
            userId = 1,
            id = 3,
            title = "ea molestias quasi exercitationem repellat qui ipsa sit aut",
            body = "et iusto sed quo iure\nvoluptatem occaecati omnis eligendi aut ad\nvoluptatem doloribus vel accusantium quis pariatur\nmolestiae porro eius odio et labore et velit aut"
        ),
        Post(
            userId = 1,
            id = 4,
            title = "eum et est occaecati",
            body = "ullam et saepe reiciendis voluptatem adipisci\nsit amet autem assumenda provident rerum culpa\nquis hic commodi nesciunt rem tenetur doloremque ipsam iure\nquis sunt voluptatem rerum illo velit"
        ),
        Post(
            userId = 1,
            id = 5,
            title = "nesciunt quas odio",
            body = "repudiandae veniam quaerat sunt sed\nalias aut fugiat sit autem sed est\nvoluptatem omnis possimus esse voluptatibus quis\nest aut tenetur dolor neque"
        ),
        Post(
            userId = 1,
            id = 6,
            title = "dolorem eum magni eos aperiam quia",
            body = "ut aspernatur corporis harum nihil quis provident sequi\nmollitia nobis aliquid molestiae\nperspiciatis et ea nemo ab reprehenderit accusantium quas\nvoluptate dolores velit et doloremque molestiae"
        ),
        Post(
            userId = 1,
            id = 7,
            title = "magnam facilis autem",
            body = "dolore placeat quibusdam ea quo vitae\nmagni quis enim qui quis quo nemo aut saepe\nquidem repellat excepturi ut quia\nsunt ut sequi eos ea sed quas"
        ),
        Post(
            userId = 1,
            id = 8,
            title = "dolorem dolore est ipsam",
            body = "dignissimos aperiam dolorem qui eum\nfacilis quibusdam animi sint suscipit qui sint possimus cum\nquaerat magni maiores excepturi\nipsam ut commodi dolor voluptatum modi aut vitae"
        ),
        Post(
            userId = 1,
            id = 9,
            title = "nesciunt iure omnis dolorem tempora et accusantium",
            body = "consectetur animi nesciunt iure dolore\nenim quia ad\nveniam autem ut quam aut nobis\net est aut quod aut provident voluptas autem voluptas"
        ),
        Post(
            userId = 1,
            id = 10,
            title = "optio molestias id quia eum",
            body = "quo et expedita modi cum officia vel magni\ndoloribus qui repudiandae\nvero nisi sit\nquos veniam quod sed accusamus veritatis error"
        ),
        Post(
            userId = 2,
            id = 11,
            title = "et ea vero quia laudantium autem",
            body = "delectus reiciendis molestiae occaecati non minima eveniet qui voluptatibus\naccusamus in eum beatae sit\nvel qui neque voluptates ut commodi qui incidunt\nut animi commodi"
        ),
        Post(
            userId = 2,
            id = 12,
            title = "in quibusdam tempore odit est dolorem",
            body = "itaque id aut magnam\npraesentium quia et ea odit et ea voluptas et\nsapiente quia nihil amet occaecati quia id voluptatem\nincidunt ea est distinctio odio"
        ),
        Post(
            userId = 2,
            id = 13,
            title = "dolorum ut in voluptas mollitia et saepe quo animi",
            body = "aut dicta possimus sint mollitia voluptas commodi quo doloremque\niste corrupti reiciendis voluptatem eius rerum\nsit cumque quod eligendi laborum minima\nperferendis recusandae assumenda consectetur porro architecto ipsum ipsam"
        ),
        Post(
            userId = 2,
            id = 14,
            title = "voluptatem eligendi optio",
            body = "fuga et accusamus dolorum perferendis illo voluptas\nnon doloremque neque facere\nad qui dolorum molestiae beatae\nsed aut voluptas totam sit illum"
        ),
    )

    private val comments: List<Comment> = listOf(
        Comment(
            postId = 1,
            id = 1,
            name = "id labore ex et quam laborum",
            email = "Eliseo@gardner.biz",
            body = "est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et"
        ), Comment(
            postId = 1,
            id = 2,
            name = "odio adipisci rerum aut animi",
            email = "Nikita@garfield.biz",
            body = "non et atque\noccaecati deserunt quas accusantium unde odit nobis qui voluptatem\nquia voluptas consequuntur itaque dolor\net qui rerum deleniti ut occaecati"
        ), Comment(
            postId = 1,
            id = 3,
            name = "vero eaque aliquid doloribus et culpa",
            email = "Hayden_Kunze@clementine.com",
            body = "doloribus at sed quis culpa deserunt consectetur qui praesentium\naccusamus fugiat dicta\nvoluptatem rerum ut voluptate autem\nvoluptatem repellendus aspernatur dolorem in"
        ), Comment(
            postId = 2,
            id = 4,
            name = "repellat consequatur praesentium vel minus molestias voluptatum",
            email = "Mallory_Kunze@marie.org",
            body = "ut voluptatem corrupti velit\nad voluptatem maiores\net nisi velit vero accusamus maiores\nvoluptates quia aliquid ullam eaque"
        ), Comment(
            postId = 2,
            id = 5,
            name = "provident id voluptas",
            email = "Meghan_Littel@rene.us",
            body = "sapiente assumenda est voluptatem vel cumque\nut in tempore eos beatae est\nvoluptate iusto quis nobis reprehenderit ipsum amet nulla\nquia quas dolores velit et non\naut quia necessitatibus"
        ), Comment(
            postId = 3,
            id = 6,
            name = "fugit labore quia mollitia quas deserunt nostrum sunt",
            email = "Veronica_Goodwin@tennessee.edu",
            body = "expedita maiores dignissimos facilis\nipsum est rem est fugit velit sequi\neum odio dolores dolor totam\noccaecati ratione eius rem velit"
        ), Comment(
            postId = 3,
            id = 7,
            name = "aut inventore non pariatur sit vitae voluptatem sapiente",
            email = "Kiara_Mavis@benjamin.info",
            body = "vel quae voluptas qui exercitationem\nvoluptatibus unde sed\nminima et qui ipsam aspernatur\nexpedita magnam laudantium et quaerat ut qui dolorum"
        ), Comment(
            postId = 3,
            id = 8,
            name = "debitis magnam hic odit aut ullam nostrum tenetur",
            email = "Christine@ayana.info",
            body = "iste ut laborum aliquid velit facere itaque\nquo ut soluta dicta voluptate\nerror tempore aut et\nsequi reiciendis dignissimos expedita consequuntur libero sed fugiat facilis"
        ), Comment(
            postId = 4,
            id = 9,
            name = "eos est omnis iste natus error sit voluptatem",
            email = "Vincenza_Klocko@albertha.name",
            body = "veritatis voluptates necessitatibus maiores corrupti\nneque et exercitationem amet sit et\nullam velit sit magnam laborum\nmagni ut molestias"
        ), Comment(
            postId = 4,
            id = 10,
            name = "sed impedit rerum quia et",
            email = "Natasha_Gonzales@marie.org",
            body = "facere vel quas\nvoluptates ab optio ut\nomnis est blanditiis exercitationem\nqui sint et veritatis"
        ), Comment(
            postId = 5,
            id = 11,
            name = "porro repellendus aut tempore quis hic",
            email = "Khalil@emile.co.uk",
            body = "qui ipsa animi nostrum praesentium voluptatibus odit\nqui non impedit cum qui nostrum aliquid fuga explicabo\nvoluptatem fugit earum voluptas exercitationem temporibus dignissimos distinctio\nesse inventore reprehenderit quidem ut incidunt nihil necessitatibus rerum"
        ), Comment(
            postId = 5,
            id = 12,
            name = "in tempore eos beatae est",
            email = "Jeffery@juwan.us",
            body = "repudiandae repellat quia\nsequi est dolore explicabo nihil et\net sit et\net praesentium iste atque asperiores tenetur"
        ), Comment(
            postId = 6,
            id = 13,
            name = "in deleniti sunt provident soluta ratione veniam quam praesentium",
            email = "Russel.Parker@kameron.io",
            body = "incidunt sapiente eaque dolor eos\nad est molestias\nquas sit et nihil exercitationem at cumque ullam\nnihil magnam et"
        ), Comment(
            postId = 6,
            id = 14,
            name = "quo voluptates voluptas nisi veritatis dignissimos dolores ut officiis",
            email = "Ronny@rosina.org",
            body = "voluptatem repellendus quo alias at laudantium\nmollitia quidem esse\ntemporibus consequuntur vitae rerum illum\nid corporis sit id"
        ), Comment(
            postId = 6,
            id = 15,
            name = "quasi nulla ducimus facilis non voluptas aut",
            email = "Lurline@marvin.biz",
            body = "consequuntur quia voluptate assumenda et\nautem voluptatem reiciendis ipsum animi est provident\nearum aperiam sapiente ad vitae iste\naccusantium aperiam eius qui dolore voluptatem et"
        ), Comment(
            postId = 7,
            id = 16,
            name = "dolorem architecto ut pariatur quae qui suscipit",
            email = "Maria@laurel.name",
            body = "nihil ea itaque libero illo\nofficiis quo dicta inventore consequatur voluptas voluptatem\ncorporis sed necessitatibus velit tempore\nrerum velit et temporibus"
        ), Comment(
            postId = 7,
            id = 17,
            name = "cum et sint at",
            email = "Ethelyn.Schneider@emelia.co.uk",
            body = "omnis temporibus quasi ab omnis\nfacilis et omnis illum quae quasi aut\nminus iure ex rem ut reprehenderit\nin non fugit"
        ), Comment(
            postId = 7,
            id = 18,
            name = "repellendus sapiente omnis praesentium aliquam ipsum id molestiae omnis",
            email = "Gretchen.Olson@miro.com",
            body = "aut vero est\ndolor non aut excepturi dignissimos illo nisi aut quas\naut magni quia nostrum provident magnam quas modi maxime\nvoluptatem et molestiae"
        ), Comment(
            postId = 8,
            id = 19,
            name = "beatae veniam nemo rerum voluptate quam aspernatur",
            email = "Jacky@giovanny.net",
            body = "unde voluptatem qui dicta\nvel ad aut eos error consequatur voluptatem\nadipisci doloribus qui est sit aut\nvelit aut et ea ratione eveniet iure fuga"
        ), Comment(
            postId = 8,
            id = 20,
            name = "necessitatibus ratione aut ut delectus quae ut",
            email = "Gaylord@russell.net",
            body = "at qui qui aliquid dolores cumque ut\nest qui sint harum aut\nconsectetur unde molestiae\nin facilis dignissimos"
        ), Comment(
            postId = 9,
            id = 21,
            name = "voluptas deleniti ut",
            email = "Lucio@gladys.tv",
            body = "quaerat veritatis eos debitis\naut repellat eius explicabo et\nofficiis quo sint at magni ratione et iure\nincidunt quo sequi quia dolorum beatae qui"
        ), Comment(
            postId = 9,
            id = 22,
            name = "molestias sint est voluptatem modi",
            email = "Jackeline@eva.tv",
            body = "voluptatem ut possimus laborum quae\nexercitationem maxime eum consequatur sit sapiente\nquae odit perspiciatis\ndoloremque rerum sapiente dolorum"
        ), Comment(
            postId = 9,
            id = 23,
            name = "autem illo facilis",
            email = "Marcia@name.biz",
            body = "ipsum odio harum voluptatem sunt cumque et dolores\nnihil laboriosam neque commodi qui est\nnecessitatibus id officiis omnis\nquisquam qui corrupti officiis"
        ), Comment(
            postId = 10,
            id = 24,
            name = "rerum commodi est non dolor nesciunt ut",
            email = "Pearlie.Kling@sandy.com",
            body = "occaecati laudantium ratione non cumque\nearum quod non enim soluta nisi velit similique\nnumquam ut perspiciatis\ndolorum non illum"
        ), Comment(
            postId = 10,
            id = 25,
            name = "rerum placeat quae minus iusto eligendi",
            email = "Camryn.Weimann@doris.io",
            body = "id est iure occaecati quam similique enim\nab omnih eum accusamus aut delectus\narchitecto blanditiis quia sunt\nrerum harum sit quos quia aspernatur vel corrupti inventore\nanimi dicta vel corporis"
        ), Comment(
            postId = 11,
            id = 26,
            name = "molestias et odio ut commodi omnis ex",
            email = "Laurie@lincoln.us",
            body = "totam laudantium\nimpedit nam labore repellendus enim earum aut\nconsectetur mollitia fugit qui repellat expedita sunt\naut fugiat vel illo quos aspernatur ducimus"
        ), Comment(
            postId = 11,
            id = 27,
            name = "maiores alias necessitatibus aut non",
            email = "Laverne_Price@scotty.info",
            body = "a at tempore\nbeatae aut vitae quod odit doloribus\nvoluptatem quaerat unde est ut\noccaecati non consequatur dolorita"
        ), Comment(
            postId = 11,
            id = 28,
            name = "quas pariatur quia a doloribus",
            email = "Hayden_Olson@morrisenames.com",
            body = "cum esse odio nihil reiciendis illum quaerat\nest facere quia\noccaecati sit totam fugiat in beatae\nut occaecati unde vitae nihil quidem consequatur"
        ), Comment(
            postId = 12,
            id = 29,
            name = "qui quidem sed",
            email = "Darron.Nikolaus@laylah.tv",
            body = "veritatis qui nihil\nquia reprehenderit non ullam ea iusto\nconsectetur nam voluptas ut temporibus tempore provident error\neos et nisi et voluptate"
        ), Comment(
            postId = 12,
            id = 30,
            name = "quis ducimus distinctio similique et illum minima ab libero",
            email = "Ezra_Abshire@lyda.us",
            body = "error eum quia voluptates alias repudiandae\naccusantium veritatis maiores assumenda\nquod impedit animi tempore veritatis\nanimi et officiis labore impedit blanditiis repudiandae"
        ), Comment(
            postId = 13,
            id = 31,
            name = "quidem itaque dolores quod laborum aliquid molestiae",
            email = "Vesta_Crooks@dora.us",
            body = "tempore dolorum corrupti facilis\npraesentium sunt iste recusandae\nunde quisquam similique\nalias consequuntur voluptates velit"
        ), Comment(
            postId = 13,
            id = 32,
            name = "occaecati dolor accusantium et quasi architecto aut eveniet",
            email = "Freida@brandt.tv",
            body = "sed illum quis\nut aut culpa labore aspernatur illo\ndolorem quia vitae ut aut quo repellendus est omnis\nesse at est debitis"
        ), Comment(
            postId = 13,
            id = 33,
            name = "earum ea ratione numquam",
            email = "Mollie@villanueva.com",
            body = "necessitatibus libero occaecati\nvero inventore iste assumenda veritatis\nasperiores non sit et quia omnis facere nemo explicabo\nodit quo nobis porro"
        ), Comment(
            postId = 14,
            id = 34,
            name = "omnis consequatur natus distinctio",
            email = "Dashawn.Bayer@steven.org",
            body = "sed magni accusantium numquam quidem omnis et voluptatem beatae\nquos fugit libero\nvel ipsa et nihil recusandae ea\niste nemo qui optio sit enim ut nostrum odit"
        ), Comment(
            postId = 14,
            id = 35,
            name = "necessitatibus ratione aut ut delectus quae ut",
            email = "Mariana_Parker@schuster.com",
            body = "qui harum consequatur fugiat\net eligendi perferendis at molestiae commodi ducimus\ndoloremque asperiores numquam qui\nut sit dignissimos reprehenderit tempore"
        ), Comment(
            postId = 15,
            id = 36,
            name = "quia sunt dolor suscipit expedita quis",
            email = "Sabrina.Marks@savanah.name",
            body = "quisquam voluptas ut\npariatur eos amet non\nreprehenderit voluptates numquam\nin est voluptatem dicta ipsa qui esse enim"
        ), Comment(
            postId = 15,
            id = 37,
            name = "ut non illum pariatur dolor",
            email = "Gussie_Kunde@sharon.biz",
            body = "non accusamus eum aut et est\naccusantium animi nesciunt distinctio ea quas quisquam\nsit ut voluptatem modi natus sint\nfacilis est qui molestias sunt mollitia"
        )
    )

    private val users: List<User> = listOf(
        User(
            id = 1,
            name = "Leanne Graham",
            userName = "Bret",
            email = "Sincere@april.biz",
            address = Address(
                street = "Kulas Light",
                suite = "Apt. 556",
                city = "Gwenborough",
                zipCode = "92998-3874",
                geo = Geo(
                    lat = "-37.3159",
                    lng = "81.1496"
                )
            ),
            phone = "1-770-736-8031 x56442",
            website = "hildegard.org",
            company = Company(
                name = "Romaguera-Crona",
                catchPhrase = "Multi-layered client-server neural-net",
                bs = "harness real-time e-markets"
            )
        ),
        User(
            id = 2,
            name = "Ervin Howell",
            userName = "Antonette",
            email = "Shanna@melissa.tv",
            address = Address(
                street = "Victor Plains",
                suite = "Suite 879",
                city = "Wisokyburgh",
                zipCode = "90566-7771",
                geo = Geo(
                    lat = "-43.9509",
                    lng = "-34.4618"
                )
            ),
            phone = "010-692-6593 x09125",
            website = "anastasia.net",
            company = Company(
                name = "Deckow-Crist",
                catchPhrase = "Proactive didactic contingency",
                bs = "synergize scalable supply-chains"
            )
        ),
        User(
            id = 3,
            name = "Clementine Bauch",
            userName = "Samantha",
            email = "Nathan@yesenia.net",
            address = Address(
                street = "Douglas Extension",
                suite = "Suite 847",
                city = "McKenziehaven",
                zipCode = "59590-4157",
                geo = Geo(
                    lat = "-68.6102",
                    lng = "-47.0653"
                )
            ),
            phone = "1-463-123-4447",
            website = "ramiro.info",
            company = Company(
                name = "Romaguera-Jacobson",
                catchPhrase = "Face to face bifurcated interface",
                bs = "e-enable strategic applications"
            )
        ),
        User(
            id = 4,
            name = "Patricia Lebsack",
            userName = "Karianne",
            email = "Julianne.OConner@kory.org",
            address = Address(
                street = "Hoeger Mall",
                suite = "Apt. 692",
                city = "South Elvis",
                zipCode = "53919-4257",
                geo = Geo(
                    lat = "29.4572",
                    lng = "-164.2990"
                )
            ),
            phone = "493-170-9623 x156",
            website = "kale.biz",
            company = Company(
                name = "Robel-Corkery",
                catchPhrase = "Multi-tiered zero tolerance productivity",
                bs = "transition cutting-edge web services"
            )
        ),
        User(
            id = 5,
            name = "Chelsey Dietrich",
            userName = "Kamren",
            email = "Lucio_Hettinger@annie.ca",
            address = Address(
                street = "Skiles Walks",
                suite = "Suite 351",
                city = "Roscoeview",
                zipCode = "33263",
                geo = Geo(
                    lat = "-31.8129",
                    lng = "62.5342"
                )
            ),
            phone = "(254)954-1289",
            website = "demarco.info",
            company = Company(
                name = "Keebler LLC",
                catchPhrase = "User-centric fault-tolerant solution",
                bs = "revolutionize end-to-end systems"
            )
        ),
        User(
            id = 6,
            name = "Mrs. Dennis Schulist",
            userName = "Leopoldo_Corkery",
            email = "Karley_Dach@jasper.info",
            address = Address(
                street = "Norberto Crossing",
                suite = "Apt. 950",
                city = "South Christi",
                zipCode = "23505-1337",
                geo = Geo(
                    lat = "-71.4197",
                    lng = "71.7478"
                )
            ),
            phone = "1-477-935-8478 x6430",
            website = "ola.org",
            company = Company(
                name = "Considine-Lockman",
                catchPhrase = "Synchronised bottom-line interface",
                bs = "e-enable innovative applications"
            )
        ),
        User(
            id = 7,
            name = "Kurtis Weissnat",
            userName = "Elwyn.Skiles",
            email = "Telly.Hoeger@billy.biz",
            address = Address(
                street = "Rex Trail",
                suite = "Suite 280",
                city = "Howemouth",
                zipCode = "58804-1099",
                geo = Geo(
                    lat = "24.8918",
                    lng = "21.8984"
                )
            ),
            phone = "210.067.6132",
            website = "elvis.io",
            company = Company(
                name = "Johns Group",
                catchPhrase = "Configurable multimedia task-force",
                bs = "generate enterprise e-tailers"
            )
        ),
        User(
            id = 8,
            name = "Nicholas Runolfsdottir V",
            userName = "Maxime_Nienow",
            email = "Sherwood@rosamond.me",
            address = Address(
                street = "Ellsworth Summit",
                suite = "Suite 729",
                city = "Aliyaview",
                zipCode = "45169",
                geo = Geo(
                    lat = "-14.3990",
                    lng = "-120.7677"
                )
            ),
            phone = "586.493.6943 x140",
            website = "jacynthe.com",
            company = Company(
                name = "Abernathy Group",
                catchPhrase = "Implemented secondary concept",
                bs = "e-enable extensible e-tailers"
            )
        ),
        User(
            id = 9,
            name = "Glenna Reichert",
            userName = "Delphine",
            email = "Chaim_McDermott@dana.io",
            address = Address(
                street = "Dayna Park",
                suite = "Suite 449",
                city = "Bartholomebury",
                zipCode = "76495-3109",
                geo = Geo(
                    lat = "24.6463",
                    lng = "-168.8889"
                )
            ),
            phone = "(775)976-6794 x41206",
            website = "conrad.com",
            company = Company(
                name = "Yost and Sons",
                catchPhrase = "Switchable contextually-based project",
                bs = "aggregate real-time technologies"
            )
        ),
        User(
            id = 10,
            name = "Clementina DuBuque",
            userName = "Moriah.Stanton",
            email = "Rey.Padberg@karina.biz",
            address = Address(
                street = "Kattie Turnpike",
                suite = "Suite 198",
                city = "Lebsackbury",
                zipCode = "31428-2261",
                geo = Geo(
                    lat = "-38.2386",
                    lng = "57.2232"
                )
            ),
            phone = "024-648-3804",
            website = "ambrose.net",
            company = Company(
                name = "Hoeger LLC",
                catchPhrase = "Centralized empowering task-force",
                bs = "target end-to-end models"
            )
        )
    )

    /** Получаем список постов */
    fun getPosts(): List<Post> {
        return posts;
    }

    /** Получаем комментарии по id поста */
    fun getCommentsByPost(postId: Int): List<Comment> {
        return comments.filter { it.postId == postId }
    }

    /** Получаем список пользователей */
    fun getUsers(): List<User> {
        return users
    }
}