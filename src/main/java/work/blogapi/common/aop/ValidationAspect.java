package work.blogapi.common.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import work.blogapi.exception.exceptions.BoardRequestValidationException;

@RequiredArgsConstructor
@Component
@Aspect
public class ValidationAspect {

    /*
    API 요청시 Validation check를 위함.
     */
    @Around(value = "execution(* work.blogapi.controller.BoardApiController.*(..))")
    public Object checkValidation(ProceedingJoinPoint pjp) throws Throwable {

        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult result = (BindingResult) arg;
                if (result.hasErrors()) {
                    StringBuilder errorMessage = makeErrorMessage(result);
                    throw new BoardRequestValidationException(errorMessage.toString());
                }
                break;
            }
        }
        return pjp.proceed();
    }

    private StringBuilder makeErrorMessage(BindingResult result) {
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : result.getFieldErrors()) {
            errorMessage.append(String.format("%s\n", fieldError.getDefaultMessage()));
        }
        return errorMessage;
    }
}
