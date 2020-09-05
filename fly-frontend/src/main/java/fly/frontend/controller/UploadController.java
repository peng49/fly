package fly.frontend.controller;

import fly.frontend.entity.User;
import fly.frontend.service.UserService;
import fly.frontend.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/upload")
public class UploadController {

}
